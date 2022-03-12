package net.muniere.sketchbook.orb.steeringBehaviors

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Move2D
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.physics.Velocity2D
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.Sketch
import net.muniere.sketchbook.lib.processing.background
import kotlin.random.Random

public final class Sketch(size: Size2D) : Sketch(size) {

  private object Params {
    internal val CANVAS_COLOR = Colors.parse("#222222")

    internal val TEXT_COLOR = Colors.parse("#FFFFFF")
    internal const val TEXT_WORD = "hello, world"
    internal const val FONT_SIZE = 160.0F
    internal const val FONT_STEP = 15.0F

    internal val VEHICLE_ATTRACTION = GravitationField(distance = 100.0F, factor = 1.0F)
    internal val VEHICLE_REPULSION = GravitationField(distance = 50.0F, factor = 2.0F)
    internal const val VEHICLE_RADIUS = 2.0F
    internal const val VEHICLE_SPEED_MAX = 10.0F
    internal const val VEHICLE_FORCE_MAX = 1.0F
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g)
  )

  override fun doSetup() {
    super.doSetup()

    val factory = PathFactory().also {
      it.textSize = Params.FONT_SIZE
      it.stepSize = Params.FONT_STEP
    }

    val path = factory.analyze(Params.TEXT_WORD).let {
      it + Move2D((this.width - it.width()) / 2, (this.height - it.height()) / 2)
    }

    val vehicles = path.points.map { anchor ->
      VehicleModel(
        radius = Params.VEHICLE_RADIUS,
        center = Point2D.zero(),
        velocity = Velocity2D(
          x = Random.nextFloat(),
          y = Random.nextFloat(),
        ),
      ).also {
        it.anchor = anchor.copy()
        it.strokeColor = Params.TEXT_COLOR
        it.attraction = Params.VEHICLE_ATTRACTION
        it.repulsion = Params.VEHICLE_REPULSION
        it.maxSpeed = Params.VEHICLE_SPEED_MAX
        it.maxForce = Params.VEHICLE_FORCE_MAX
      }
    }

    this.model = ApplicationModel(vehicles)

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
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

  override fun touchStarted() {
    this.model.repulsion = Point2D(
      x = this.mouseX.toFloat(),
      y = this.mouseY.toFloat(),
    )
  }

  override fun touchMoved() {
    this.model.repulsion = Point2D(
      x = this.mouseX.toFloat(),
      y = this.mouseY.toFloat(),
    )
  }

  override fun touchEnded() {
    this.model.repulsion = null
  }
}

