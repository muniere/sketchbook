package net.muniere.sketchbook.orb.fourierTransform.real

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.math.Complex
import net.muniere.sketchbook.lib.processing.FrameClock
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.SketchApplet
import net.muniere.sketchbook.lib.processing.background
import net.muniere.sketchbook.orb.fourierTransform.PathModel
import net.muniere.sketchbook.orb.fourierTransform.SeriesModel
import net.muniere.sketchbook.orb.fourierTransform.io.PathLoader

internal final class SketchApplet : SketchApplet() {

  private object Params {
    internal object Canvas {
      internal val COLOR = Colors.parse("#222222")
    }

    internal object Sample {
      internal const val INTERVAL = 15
    }

    internal object Shape {
      internal val COLOR = Colors.parse("#FFFFFF")
      internal val ORIGIN = Point2D(50.0F, 50.0F)
      internal val MARGIN = Size2D(300.0F, 300.0F)
      internal const val TRACK_WEIGHT = 1.0F
      internal const val HAND_WEIGHT = 1.0F
      internal const val POINT_RADIUS = 0.0F
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g)
  )

  override fun doSetup() {
    val dataSet = PathLoader(this.surface.assets).load(
      interval = Params.Sample.INTERVAL
    )

    val clock = FrameClock(this, scale = (2 * Math.PI).toFloat() / dataSet.size)

    val xSeries = SeriesModel.create(
      center = Params.Shape.ORIGIN.movingBy(x = Params.Shape.MARGIN.width),
      values = dataSet.map { Complex(re = it.x) },
      decorate = { it.color = Params.Shape.COLOR }
    )
    val ySeries = SeriesModel.create(
      center = Params.Shape.ORIGIN.movingBy(y = Params.Shape.MARGIN.height),
      values = dataSet.map { Complex(re = it.y) },
      decorate = { it.color = Params.Shape.COLOR }
    )
    val path = PathModel().also {
      it.color = Params.Shape.COLOR
      it.maxLength = (dataSet.size * 0.8).toInt()
    }

    this.model = ApplicationModel(
      clock = clock,
      xSeries = xSeries,
      ySeries = ySeries,
      path = path,
    )

    this.widget = ApplicationWidget(this.g).also { widget ->
      widget.model = this.model
      widget.origin = Params.Shape.ORIGIN.copy()
      widget.xSeries.also {
        it.trackWeight = Params.Shape.TRACK_WEIGHT
        it.handWeight = Params.Shape.HAND_WEIGHT
        it.pointRadius = Params.Shape.POINT_RADIUS
      }
      widget.ySeries.also {
        it.trackWeight = Params.Shape.TRACK_WEIGHT
        it.handWeight = Params.Shape.HAND_WEIGHT
        it.pointRadius = Params.Shape.POINT_RADIUS
      }
      widget.xLine.also {
        it.weight = Params.Shape.TRACK_WEIGHT
        it.color = Params.Shape.COLOR
      }
      widget.yLine.also {
        it.weight = Params.Shape.TRACK_WEIGHT
        it.color = Params.Shape.COLOR
      }
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.Canvas.COLOR)

    // widget
    this.widget.draw()

    // update
    this.model.update()
  }

  override fun touchEnded() {
    if (this.isLooping) {
      this.noLoop()
    } else {
      this.loop()
    }
  }
}
