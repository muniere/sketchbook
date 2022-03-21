package net.muniere.sketchbook.orb.pathFinding

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.math.Dimen
import net.muniere.sketchbook.lib.processing.SketchApplet
import net.muniere.sketchbook.lib.processing.background
import kotlin.random.Random

internal final class SketchApplet : SketchApplet() {

  internal fun interface OnSuccessListener {
    public fun onSuccess()
  }

  internal fun setOnSuccessListener(listener: OnSuccessListener?) {
    this.onSuccessListener = listener
  }

  private var onSuccessListener: OnSuccessListener? = null

  internal fun interface OnFailureListener {
    public fun onFailure()
  }

  internal fun setOnFailureListener(listener: OnFailureListener?) {
    this.onFailureListener = listener
  }

  private var onFailureListener: OnFailureListener? = null

  private object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#333333")
    }

    internal object Graph {
      internal const val GRID_SCALE = 16
      internal const val WALL_RATE = 0.4

      internal val BASE_COLOR = Colors.parse("#FFFFFF")
      internal val WALL_COLOR = Colors.parse("#000000")
      internal val OPEN_COLOR = Colors.parse("#FFFF99")
      internal val CLOSED_COLOR = Colors.parse("#CCCCCC")
      internal val RUNNING_COLOR = Colors.parse("#99FFFF")
      internal val ANSWER_COLOR = Colors.parse("#00CC00")
      internal val ABORT_COLOR = Colors.parse("#FF0000")
    }
  }

  private lateinit var model: SolverModel
  private lateinit var widget: SolverWidget

  override fun doSetup() {
    val rect = Rect2D(
      origin = Point2D.zero(),
      size = Size2D(this.width.toFloat(), this.height.toFloat()),
    )
    val dimen = Dimen(
      width = this.width / Params.Graph.GRID_SCALE,
      height = this.height / Params.Graph.GRID_SCALE,
    )

    val graph = GraphModel.generate(rect, dimen) { spot ->
      if (spot.row == 0 && spot.column == 0) {
        return@generate NodeModel.Kind.PATH
      }
      if (spot.row == dimen.height - 1 && spot.column == dimen.width - 1) {
        return@generate NodeModel.Kind.PATH
      }
      if (Random.nextFloat() > Params.Graph.WALL_RATE) {
        return@generate NodeModel.Kind.PATH
      } else {
        return@generate NodeModel.Kind.WALL
      }
    }

    val heuristic = HeuristicModels.Manhattan()

    this.model = SolverModel(
      graph = graph,
      heuristic = heuristic,
    )

    this.widget = SolverWidget(this.g).also {
      it.model = this.model
      it.palette.baseColor = Params.Graph.BASE_COLOR
      it.palette.wallColor = Params.Graph.WALL_COLOR
      it.palette.openColor = Params.Graph.OPEN_COLOR
      it.palette.closedColor = Params.Graph.CLOSED_COLOR
      it.palette.runningColor = Params.Graph.RUNNING_COLOR
      it.palette.answerColor = Params.Graph.ANSWER_COLOR
      it.palette.abortColor = Params.Graph.ABORT_COLOR
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.Canvas.BACKGROUND_COLOR)

    // widget
    this.widget.draw()

    // update
    when (this.model.state) {
      SolverModel.State.RUNNING -> {
        this.model.next()
      }
      SolverModel.State.SUCCESS -> {
        this.onSuccessListener?.onSuccess()
        this.noLoop()
      }
      SolverModel.State.FAILURE -> {
        this.onFailureListener?.onFailure()
        this.noLoop()
      }
    }
  }

  public fun reset() {
    this.doSetup()
    this.loop()
  }
}

