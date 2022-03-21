package net.muniere.sketchbook.orb.fourierTransform.complex

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.math.Complex
import net.muniere.sketchbook.lib.processing.FrameClock
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.background
import net.muniere.sketchbook.orb.fourierTransform.PathModel
import net.muniere.sketchbook.orb.fourierTransform.SeriesModel
import net.muniere.sketchbook.orb.fourierTransform.io.PathLoader

internal final class SketchApplet : SApplet() {

  private object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#222222")
    }

    internal object Sample {
      internal const val INTERVAL = 15
    }

    internal object Shape {
      internal val ORIGIN = Point2D(50.0F, 50.0F)
      internal val MARGIN = Size2D(300.0F, 300.0F)
      internal val STROKE_COLOR = Colors.parse("#FFFFFF")
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

    val origin = Params.Shape.ORIGIN.movingBy(
      y = Params.Shape.MARGIN.height / 2
    )

    val clock = FrameClock(this, scale = (2 * Math.PI).toFloat() / dataSet.size)

    val series = SeriesModel.create(
      center = origin.movingBy(x = Params.Shape.MARGIN.width),
      values = dataSet.map { Complex(re = it.x, im = it.y) },
      decorate = { it.color = Params.Shape.STROKE_COLOR }
    )

    val path = PathModel().also {
      it.color = Params.Shape.STROKE_COLOR
      it.maxLength = (dataSet.size * 0.8).toInt()
    }

    this.model = ApplicationModel(
      clock = clock,
      chain = series,
      path = path
    )
    this.widget = ApplicationWidget(this.g).also { widget ->
      widget.model = model;
      widget.origin = origin;
      widget.chain.also {
        it.trackWeight = Params.Shape.TRACK_WEIGHT
        it.handWeight = Params.Shape.HAND_WEIGHT
        it.pointRadius = Params.Shape.POINT_RADIUS
      }
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.Canvas.BACKGROUND_COLOR);

    // widget
    this.widget.draw();

    // update
    this.model.update();
  }

  override fun touchEnded() {
    if (this.isLooping) {
      this.noLoop()
    } else {
      this.loop()
    }
  }
}
