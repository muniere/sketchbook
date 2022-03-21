package net.muniere.sketchbook.orb.fireworks

import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.physics.Acceleration2D
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.background

internal final class SketchApplet : SApplet() {

  private object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#22222255")
    }

    internal object Field {
      internal const val GRAVITY = 0.1F
    }

    internal object Fireworks {
      internal const val COUNT = 20
      internal val RADIUS_RANGE = FloatRange(4.0F, 8.0F)
      internal val SPEED_RANGE = FloatRange(-6.0F, -14.0F)
      internal val LIFESPAN_RANGE = 200..500
    }

    internal object Explosion {
      internal const val SCALE = 1.0F / 3.0F
      internal const val COUNT = 50
      internal val SPEED_RANGE = FloatRange(-2.0F, 2.0F)
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g)
  )

  override fun doSetup() {
    val frame = Rect2D(
      origin = Point2D.zero(),
      size = this.size,
    )

    val gravity = Acceleration2D(
      x = 0.0F,
      y = Params.Field.GRAVITY,
    )

    val ignition = RandomIgnitionModel().also {
      it.radiusRange = Params.Fireworks.RADIUS_RANGE
      it.speedRange = Params.Fireworks.SPEED_RANGE
      it.lifespanRange = Params.Fireworks.LIFESPAN_RANGE
    }

    val fireworks = List(Params.Fireworks.COUNT) {
      FireworkModel(
        explosion = RandomExplosionModel().also {
          it.count = Params.Explosion.COUNT
          it.scale = Params.Explosion.SCALE
          it.range = Params.Explosion.SPEED_RANGE
        },
      )
    }

    this.model = ApplicationModel(
      frame = frame,
      gravity = gravity,
      ignition = ignition,
      fireworks = fireworks,
    )

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
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
