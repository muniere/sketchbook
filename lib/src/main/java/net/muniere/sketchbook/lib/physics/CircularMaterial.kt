package net.muniere.sketchbook.lib.physics

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D

public open class CircularMaterial(
  public var radius: Float,
) : Material() {

  override val top: Float
    get() = this.center.y - this.radius

  override val bottom: Float
    get() = this.center.y + this.radius

  override val left: Float
    get() = this.center.x - this.radius

  override val right: Float
    get() = this.center.x + this.radius

  override fun apply(force: Force2D) {
    this.acceleration.add(force.acceleration(mass = this.mass))
  }

  override fun update() {
    this.velocity.add(this.acceleration)
    this.center.add(this.velocity.toMove())
    this.acceleration.setMagnitude(0.0F)
  }

  override fun moveTo(point: Point2D) {
    this.center.assign(point)
  }

  override fun bounceIn(frame: Rect2D) {
    if (this.left <= frame.left || frame.right <= this.right) {
      this.velocity.add(
        Acceleration2D(x = -this.velocity.x * 2, y = 0.0F)
      )
    }
    if (this.top <= frame.top || frame.bottom <= this.bottom) {
      this.velocity.add(
        Acceleration2D(x = 0.0F, y = -this.velocity.y * 2)
      )
    }
  }

  public fun intersects(other: CircularMaterial): Boolean {
    return Point2D.dist(this.center, other.center) < this.radius + other.radius
  }
}
