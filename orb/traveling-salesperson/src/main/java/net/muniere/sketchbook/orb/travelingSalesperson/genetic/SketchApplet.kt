package net.muniere.sketchbook.orb.travelingSalesperson.genetic

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.background
import net.muniere.sketchbook.orb.travelingSalesperson.PathModel
import kotlin.random.Random

class SketchApplet : SApplet() {

  public object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#222222")
    }

    internal object Label {
      internal const val MARGIN = 20.0F
    }

    internal object Genetics {
      internal const val CONCURRENCY = 10
      internal const val MUTATION_RATE = 0.8F
      internal const val MUTATION_DEPTH = 5
      internal const val GENERATION_LIMIT = 100
    }

    internal object Path {
      internal val STROKE_COLOR = Colors.parse("#FFFFFF")
      internal const val STROKE_WEIGHT = 1.0F
      internal const val NODE_COUNT = 6
    }

    internal object Candidate {
      internal val STROKE_COLOR = Colors.parse("#FFFF22")
      internal const val STROKE_WEIGHT = 5.0F
    }

    internal object Answer {
      internal val STROKE_COLOR = Colors.parse("#FF22FF")
      internal const val STROKE_WEIGHT = 5.0F
    }
  }

  private lateinit var model: SolverModel
  private lateinit var widget: SolverWidget

  override fun doSetup() {
    val points = List(Params.Path.NODE_COUNT) {
      Point2D(
        x = Random.nextFloat() * this.width,
        y = Random.nextFloat() * this.height,
      )
    }

    val paths = List(Params.Genetics.CONCURRENCY) {
      PathModel(points.shuffled())
    }
    val cross = CrossModels.Joint()

    val mutation = MutationModels.Noise(
      rate = Params.Genetics.MUTATION_RATE,
      depth = Params.Genetics.MUTATION_DEPTH,
    )

    val group = PathGroupModel(
      paths = paths,
      cross = cross,
      mutation = mutation
    )

    this.model = SolverModel(
      group = group,
      limit = Params.Genetics.GENERATION_LIMIT
    );

    this.widget = SolverWidget(this.g).also {
      it.model = this.model
      it.pathColor = Params.Path.STROKE_COLOR
      it.pathWeight = Params.Path.STROKE_WEIGHT
      it.candidateColor = Params.Candidate.STROKE_COLOR
      it.candidateWeight = Params.Candidate.STROKE_WEIGHT
      it.answerColor = Params.Answer.STROKE_COLOR
      it.answerWeight = Params.Answer.STROKE_WEIGHT
      it.labelOrigin = Point2D(
        x = this.width - Params.Label.MARGIN,
        y = Params.Label.MARGIN,
      )
    }
  }

  override fun doDraw() {
    // draw
    this.g.background(Params.Canvas.BACKGROUND_COLOR);

    // update
    this.model.next()

    // widget
    this.widget.draw()

    if (!this.model.hasNext()) {
      this.postNoLoop()
    }
  }
}
