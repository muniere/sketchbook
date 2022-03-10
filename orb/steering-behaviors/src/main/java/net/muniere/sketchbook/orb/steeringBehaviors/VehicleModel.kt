package net.muniere.sketchbook.orb.steeringBehaviors

import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.map
import net.muniere.sketchbook.lib.physics.CircularMaterial
import net.muniere.sketchbook.lib.physics.Force2D
import net.muniere.sketchbook.lib.physics.Velocity2D
import processing.core.PVector

internal class VehicleModel(
  radius: Float,
  center: Point2D,
  velocity: Velocity2D,
) : CircularMaterial(radius, center = center, velocity = velocity) {
  public var anchor = Point2D.zero()
  public var attraction = GravitationField(0.0F, 1.0F)
  public var repulsion = GravitationField(0.0F, 1.0F)
  public var maxSpeed: Float = 0.0F
  public var maxForce: Float = 0.0F

  internal fun attract() {
    val desired = PVector(
      this.anchor.x - this.center.x,
      this.anchor.y - this.center.y,
    )

    val distance = desired.mag()

    val magnitude = when (distance > this.attraction.distance) {
      true -> this.maxSpeed
      false -> distance.map(
        domain = FloatRange(0.0F, this.attraction.distance),
        target = FloatRange(0.0F, this.maxSpeed),
      )
    }

    desired.setMag(magnitude)

    val steer = desired.also {
      it.sub(this.velocity.toVector())
      it.limit(this.maxForce)
      it.mult(this.mass)
    }

    val force = steer.mult(this.attraction.factor).let(::Force2D)

    this.apply(force)
  }

  internal fun repulse(point: Point2D) {
    val desired = PVector(
      this.center.x - point.x,
      this.center.y - point.y,
    )

    val distance = desired.mag()
    if (distance > this.repulsion.distance) {
      return
    }

    desired.setMag(this.maxSpeed)

    val steer = desired.also {
      it.sub(this.velocity.toVector())
      it.limit(this.maxForce)
      it.mult(this.mass)
    }

    val force = steer.mult(this.repulsion.factor).let(::Force2D)

    this.apply(force)
  }
}
