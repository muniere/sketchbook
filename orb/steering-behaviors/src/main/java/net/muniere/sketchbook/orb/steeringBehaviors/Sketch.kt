package net.muniere.sketchbook.orb.steeringBehaviors

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Move2D
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.physics.Velocity2D
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.Sketch
import net.muniere.sketchbook.lib.processing.background
import kotlin.random.Random

public final class Sketch : Sketch() {

  private object Params {
    internal object Canvas {
      internal val COLOR = Colors.parse("#222222")
    }

    internal object Text {
      internal val COLOR = Colors.parse("#FFFFFF")
      internal const val VALUE = "hello, world"
      internal const val FONT_SIZE = 160.0F
      internal const val PATH_STEP = 15.0F
    }

    internal object Vehicle {
      internal val ATTRACTION = GravitationField(distance = 100.0F, factor = 1.0F)
      internal val REPULSION = GravitationField(distance = 50.0F, factor = 2.0F)
      internal const val RADIUS = 2.0F
      internal const val SPEED_MAX = 10.0F
      internal const val FORCE_MAX = 1.0F
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g)
  )

  override fun doSetup() {
    super.doSetup()

    val factory = PathFactory().also {
      it.textSize = Params.Text.FONT_SIZE
      it.stepSize = Params.Text.PATH_STEP
    }

    val path = factory.analyze(Params.Text.VALUE).let {
      it + Move2D((this.width - it.width()) / 2, (this.height - it.height()) / 2)
    }

    val vehicles = path.points.map { anchor ->
      VehicleModel(
        radius = Params.Vehicle.RADIUS,
        center = Point2D.zero(),
        velocity = Velocity2D(
          x = Random.nextFloat(),
          y = Random.nextFloat(),
        ),
      ).also {
        it.anchor = anchor.copy()
        it.strokeColor = Params.Text.COLOR
        it.attraction = Params.Vehicle.ATTRACTION
        it.repulsion = Params.Vehicle.REPULSION
        it.maxSpeed = Params.Vehicle.SPEED_MAX
        it.maxForce = Params.Vehicle.FORCE_MAX
      }
    }

    this.model = ApplicationModel(vehicles)

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
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

