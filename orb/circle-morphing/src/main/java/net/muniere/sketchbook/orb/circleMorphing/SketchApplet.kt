package net.muniere.sketchbook.orb.circleMorphing

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.SketchApplet
import net.muniere.sketchbook.lib.processing.background

internal final class SketchApplet : SketchApplet() {

  private object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#CCCCCC")
    }

    internal object Metrics {
      internal val TEXT_COLOR = Colors.parse("#000000")
    }

    internal object Shape {
      internal val STROKE_COLOR = Colors.parse("#000000")
      internal const val RESOLUTION = 360 / 2

      internal object Source {
        internal const val RADIUS = 150.0F
        internal const val VERTEX_N = 0
      }

      internal object Destination {
        internal const val RADIUS = 80.0F
        internal const val VERTEX_N = 5
      }
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g).also {
      it.textColor = Params.Metrics.TEXT_COLOR
    }
  )

  override fun doSetup() {
    val src = when (Params.Shape.Source.VERTEX_N >= 3) {
      true -> PathModels.polygon(
        n = Params.Shape.Source.VERTEX_N,
        radius = Params.Shape.Source.RADIUS,
        resolution = Params.Shape.RESOLUTION,
      )
      false -> PathModels.circle(
        radius = Params.Shape.Source.RADIUS,
        resolution = Params.Shape.RESOLUTION,
      )
    }

    val dst = when (Params.Shape.Destination.VERTEX_N >= 3) {
      true -> PathModels.polygon(
        n = Params.Shape.Destination.VERTEX_N,
        radius = Params.Shape.Destination.RADIUS,
        resolution = Params.Shape.RESOLUTION,
      )
      false -> PathModels.circle(
        radius = Params.Shape.Destination.RADIUS,
        resolution = Params.Shape.RESOLUTION,
      )
    }

    this.model = ApplicationModel(
      morphing = InterpolationMorphingModel(
        src = src,
        dst = dst,
      )
    )

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
      it.color = Params.Shape.STROKE_COLOR
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.Canvas.BACKGROUND_COLOR)
    this.g.translate(this.width.toFloat() / 2, this.height.toFloat() / 2)

    // widget
    this.widget.draw()

    // update
    this.model.update()
  }
}
