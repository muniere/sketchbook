package net.muniere.sketchbook.lib.physics

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D

public open class RectangularMaterial(
  public var size: Size2D,
) : Material() {

  public val width: Float
    get() = this.size.width

  public val height: Float
    get() = this.size.height

  override val top: Float
    get() = this.center.y - this.height / 2

  override val bottom: Float
    get() = this.center.y + this.height / 2

  override val left: Float
    get() = this.center.x - this.width / 2

  override val right: Float
    get() = this.center.x + this.width / 2

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

  public fun intersects(other: RectangularMaterial): Boolean {
    if (other.left > this.right) {
      return false
    }
    if (other.right < this.left) {
      return false
    }
    if (other.top > this.bottom) {
      return false
    }
    if (other.bottom < this.top) {
      return false
    }
    return true
  }
}
