package net.muniere.sketchbook.orb.bezierCurve

import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.physics.Velocity2D
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.Sketch
import net.muniere.sketchbook.lib.processing.background
import processing.core.PVector
import kotlin.random.Random

public final class Sketch(size: Size2D) : Sketch(size) {

  private object Params {
    internal val CANVAS_COLOR = Colors.parse("#222222")

    internal const val PATH_MARGIN = 10.0F
    internal const val PATH_RESOLUTION = 50

    internal const val CONTROL_COUNT = 2
    internal const val CONTROL_RADIUS = 5.0F
    internal const val CONTROL_SPEED_MAX = 10.0F
    internal const val CONTROL_SPEED_MIN = 1.0F
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g)
  )

  override fun doSetup() {
    val speedRange = FloatRange(
      Params.CONTROL_SPEED_MIN,
      Params.CONTROL_SPEED_MAX,
    )

    val frame = Rect2D(
      origin = Point2D.zero(),
      size = this.size,
    )

    val vehicles = List(Params.CONTROL_COUNT) {
      VehicleModel(
        radius = Params.CONTROL_RADIUS,
        center = Point2D(
          x = Random.nextFloat() * this.width,
          y = Random.nextFloat() * this.height,
        ),
        velocity = Velocity2D(PVector.random2D()).withMagnitude(speedRange.random()),
      )
    }

    val calculator = CalculatorModel(
      start = Point2D(
        x = Params.PATH_MARGIN,
        y = this.height.toFloat() / 2.0F,
      ),
      stop = Point2D(
        x = this.width.toFloat() - Params.PATH_MARGIN,
        y = this.height.toFloat() / 2.0F,
      ),
      controls = emptyList(),
      resolution = Params.PATH_RESOLUTION,
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
    this.g.background(Params.CANVAS_COLOR)

    // widget
    this.widget.draw()

    // update
    this.model.update()
  }

  override fun touchEnded() {
    when (this.isLooping) {
      true -> this.noLoop()
      false -> this.loop()
    }
  }
}
