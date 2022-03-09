package net.muniere.sketchbook.orb.fireworks

import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.physics.CircularMaterial
import net.muniere.sketchbook.lib.physics.Velocity2D

internal interface ExplosionModel {
  public fun perform(core: CircularMaterial): List<CircularMaterial>
}

internal class RandomExplosionModel : ExplosionModel {
  public var count: Int = 1
  public var scale: Float = 1.0F
  public var range: FloatRange = FloatRange(-1.0F, 1.0F)

  override fun perform(core: CircularMaterial): List<CircularMaterial> {
    return List(this.count) {
      CircularMaterial(
        radius = core.radius * this.scale,
        center = core.center.copy(),
        velocity = Velocity2D(
          x = this.range.random(),
          y = this.range.random(),
        ),
      ).also {
        it.fillColor = core.fillColor
      }
    }
  }
}
