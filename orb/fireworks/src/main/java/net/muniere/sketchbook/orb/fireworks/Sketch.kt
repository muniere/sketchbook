package net.muniere.sketchbook.orb.fireworks

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.physics.Acceleration2D
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.Plugin
import net.muniere.sketchbook.lib.processing.Sketch
import net.muniere.sketchbook.lib.processing.background

public final class Sketch(size: Size2D) : Sketch(size) {

  private object Params {
    internal val CANVAS_COLOR = Color.valueOf(0x22222255)
    internal val PARTICLE_COLOR = Color.valueOf(0xFFFFFF)
    internal val FIREWORKS_COUNT = 20
    internal val FIREWORKS_RADIUS_RANGE = 4.0F..8.0F
    internal val FIREWORKS_SPEED_RANGE = -6.0F..-14.0F
    internal val FIREWORKS_LIFESPAN_RANGE = 200..500
    internal val EXPLOSION_SCALE = 1.0F / 3.0F
    internal val EXPLOSION_COUNT = 50
    internal val EXPLOSION_SPEED_RANGE = -2.0F..2.0F
    internal val GRAVITY_VALUE = 0.1F
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g)
  )

  override fun doSetup() {
    this.model = ApplicationModel(
      frame = Rect2D(
        origin = Point2D.zero(),
        size = this.size,
      ),
      gravity = Acceleration2D(
        x = 0.0F,
        y = Params.GRAVITY_VALUE,
      ),
      ignition = RandomIgnitionModel().also {
        it.radiusRange = Params.FIREWORKS_RADIUS_RANGE
        it.speedRange = Params.FIREWORKS_SPEED_RANGE
        it.lifespanRange = Params.FIREWORKS_LIFESPAN_RANGE
      },
      fireworks = List(Params.FIREWORKS_COUNT) {
        FireworkModel(
          explosion = RandomExplosionModel().also {
            it.count = Params.EXPLOSION_COUNT
            it.scale = Params.EXPLOSION_SCALE
            it.range = Params.EXPLOSION_SPEED_RANGE
          },
        )
      }
    )
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
}
