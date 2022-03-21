package net.muniere.sketchbook.orb.fourierSeries

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.processing.FrameClock
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.SketchApplet
import net.muniere.sketchbook.lib.processing.background

internal final class SketchApplet : SketchApplet() {

  private object Params {
    internal object Canvas {
      internal val COLOR = Colors.parse("#222222")
    }

    internal object Clock {
      internal const val SCALE = -1 * (Math.PI / 120).toFloat()
    }

    internal object Shape {
      internal val COLOR = Colors.parse("#FFFFFF")
      internal val ORIGIN = Point2D(50.0F, 50.0F)
      internal const val SPACING = 50.0F
    }

    internal object Series {
      internal const val RADIUS = 200.0F
      internal const val DEPTH = 50
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g).also {
      it.textColor = Params.Shape.COLOR
    }
  )

  override fun doSetup() {
    super.doSetup()

    val clock = FrameClock(this, scale = Params.Clock.SCALE)

    val series = SeriesModel.create(
      amplitude = Params.Series.RADIUS,
      depth = Params.Series.DEPTH,
    ).also {
      it.color = Params.Shape.COLOR
    }

    val path = PathModel().also {
      it.color = Params.Shape.COLOR
    }

    this.model = ApplicationModel(
      clock = clock,
      series = series,
      path = path
    )

    this.widget = ApplicationWidget(this.g).also { widget ->
      widget.model = this.model

      widget.origin = Point2D(
        x = Params.Shape.ORIGIN.x + Params.Series.RADIUS,
        y = Params.Shape.ORIGIN.y + Params.Series.RADIUS,
      )

      widget.series.also {
        it.origin = Point2D.zero()
      }

      widget.path.also {
        it.origin = Point2D(
          x = Params.Series.RADIUS + Params.Shape.SPACING,
          y = 0.0F,
        )
        it.scaleX = 1.0F
        it.scaleY = 1.0F
      }

      widget.line.also {
        it.color = Params.Shape.COLOR
      }
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.Canvas.COLOR)

    // update
    this.model.update()

    // draw
    this.widget.draw()
  }

  override fun touchEnded() {
    if (this.isLooping) {
      this.noLoop()
    } else {
      this.loop()
    }
  }
}
