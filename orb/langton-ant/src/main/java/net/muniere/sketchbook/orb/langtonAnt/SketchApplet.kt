package net.muniere.sketchbook.orb.langtonAnt

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.math.Dimen
import net.muniere.sketchbook.lib.atlas.Direction
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.background

internal final class SketchApplet : SApplet() {

  private object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#FFFFFF")
    }

    internal object Grid {
      internal val DIMEN = Dimen.square(300)
      internal val FILL_COLOR = Colors.parse("#000000")
      internal val LINE_COLOR = Colors.parse("#CCCCCC")
    }

    internal object Ant {
      internal const val COUNT = 5
      internal val FILL_COLOR = Colors.parse("#FF00FF")
    }

    internal object Step {
      internal val TEXT_COLOR = Colors.BLACK
    }

    internal object World {
      internal const val SPEED = 100
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g).also {
      it.textColor = Colors.BLACK
    }
  )

  override fun doSetup() {
    super.doSetup()

    val dimen = Params.Grid.DIMEN

    val directions = listOf(
      Direction.NORTH,
      Direction.SOUTH,
      Direction.WEST,
      Direction.EAST,
    )

    val ants = List(Params.Ant.COUNT) {
      AntModel(
        spot = dimen.random(),
        direction = directions.random(),
      )
    }

    val grid = GridModel.create(dimen)

    this.model = ApplicationModel(
      ants = ants,
      grid = grid
    )

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
      it.frame.size = this.size
      it.fillColor = Params.Grid.FILL_COLOR
      it.lineColor = Params.Grid.LINE_COLOR
      it.antColor = Params.Ant.FILL_COLOR
      it.textColor = Params.Step.TEXT_COLOR
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.Canvas.BACKGROUND_COLOR)

    // widget
    this.widget.draw()

    // update
    repeat(Params.World.SPEED) {
      this.model.update()
    }
  }
}
