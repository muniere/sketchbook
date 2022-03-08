package net.muniere.sketchbook.orb.purpleRain

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.Point3D
import net.muniere.sketchbook.lib.map
import net.muniere.sketchbook.lib.physics.Acceleration3D
import net.muniere.sketchbook.lib.physics.Velocity3D

internal final class DropModel(
  public val origin: Point3D,
  public val length: Float,
) {
  public var color: Color = Color.valueOf(Color.WHITE)

  public val point: Point3D = origin.copy()
  private val velocity: Velocity3D = Velocity3D.zero()

  public fun update() {
    val gravity = this.point.z.map(
      domain = 0.0F..20.0F,
      target = 0.0F..0.01F,
    )

    val accel = Acceleration3D(
      x = 0.0F,
      y = gravity,
      z = 0.0F,
    )

    this.point.y += this.velocity.y
    this.velocity.plusAssign(accel)
  }

  public fun reset() {
    this.point.assign(this.origin)
    this.velocity.setMagnitude(0.0F)
  }
}
