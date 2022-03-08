package net.muniere.sketchbook.orb.starField

import net.muniere.sketchbook.lib.graphics.Point3D
import net.muniere.sketchbook.lib.physics.Acceleration3D
import net.muniere.sketchbook.lib.physics.Velocity3D

internal final class StarModel(
  public val radius: Float,
  public val center: Point3D,
) {

  public val origin: Point3D = center.copy()
  public val velocity: Velocity3D = Velocity3D.zero()

  public var speed: Float
    get() {
      return this.velocity.magnitude()
    }
    set(value) {
      if (value > 0 && this.velocity.magnitude() == 0.0F) {
        val accel = Acceleration3D(x = 0.0F, y = 0.0F, z = 1.0F)
        this.velocity.plusAssign(accel)
        this.velocity.normalize()
      }

      this.velocity.setMagnitude(value)
    }

  public fun update() {
    this.center.z -= this.speed

    if (this.center.z < 0.01) {
      this.reset()
    }
  }

  public fun reset() {
    this.center.z = this.origin.z
  }
}
