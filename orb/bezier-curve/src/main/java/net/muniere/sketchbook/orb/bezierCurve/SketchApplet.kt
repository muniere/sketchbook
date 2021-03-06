package net.muniere.sketchbook.orb.bezierCurve

import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.physics.Velocity2D
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.background
import processing.core.PVector
import kotlin.random.Random

internal final class SketchApplet : SApplet() {

  private object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#222222")
    }

    internal object Path {
      internal const val MARGIN = 10.0F
      internal const val RESOLUTION = 50
    }

    internal object Control {
      internal const val COUNT = 2
      internal const val RADIUS = 5.0F
      internal const val SPEED_MAX = 10.0F
      internal const val SPEED_MIN = 1.0F
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g)
  )

  override fun doSetup() {
    val speedRange = FloatRange(
      Params.Control.SPEED_MIN,
      Params.Control.SPEED_MAX,
    )

    val frame = Rect2D(
      origin = Point2D.zero(),
      size = this.size,
    )

    val vehicles = List(Params.Control.COUNT) {
      VehicleModel(
        radius = Params.Control.RADIUS,
        center = Point2D(
          x = Random.nextFloat() * this.width,
          y = Random.nextFloat() * this.height,
        ),
        velocity = Velocity2D(PVector.random2D()).withMagnitude(speedRange.random()),
      )
    }

    val calculator = CalculatorModel(
      start = Point2D(
        x = Params.Path.MARGIN,
        y = this.height.toFloat() / 2.0F,
      ),
      stop = Point2D(
        x = this.width.toFloat() - Params.Path.MARGIN,
        y = this.height.toFloat() / 2.0F,
      ),
      controls = emptyList(),
      resolution = Params.Path.RESOLUTION,
    )

    this.model = ApplicationModel(
      frame = frame,
      vehicles = vehicles,
      calculator = calculator
    )

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
      it.components = listOf(
        PathWidget.Component.Auxiliary,
        PathWidget.Component.Curve(PathStyle.CONTINUOUS)
      )
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.Canvas.BACKGROUND_COLOR)

    // widget
    this.widget.draw()

    // update
    this.model.update()
  }
}
