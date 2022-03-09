package net.muniere.sketchbook.orb.circlePacking

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D

internal final class CircleModel(
  radius: Float,
  center: Point2D,
) {

  public var radius: Float = radius
    private set

  public var center: Point2D = center
    private set

  public var isGrowing: Boolean = false
    private set

  public var strokeWeight: Float = 1.0F
  public var strokeColor: Color? = Color.valueOf(Color.WHITE)
  public var fillColor: Color? = null

  companion object {
    public fun dist(a: CircleModel, b: CircleModel): Float {
      return Point2D.dist(a.center, b.center)
    }
  }

  public val top: Float
    get() = this.center.y - this.radius

  public val left: Float
    get() = this.center.x - this.radius

  public val right: Float
    get() = this.center.x + this.radius

  public val bottom: Float
    get() = this.center.y + this.radius

  public fun update() {
    if (this.isGrowing) {
      this.radius += 1.0F
    }
  }

  public fun startGrow() {
    this.isGrowing = true
  }

  public fun stopGrow() {
    this.isGrowing = false
  }

  public fun includes(point: Point2D): Boolean {
    return Point2D.dist(this.center, point) < this.radius
  }

  public fun intersects(circle: CircleModel): Boolean {
    return CircleModel.dist(this, circle) < this.radius + circle.radius
  }

  public fun intersects(others: Collection<CircleModel>): Boolean {
    return others.any { this != it && this.intersects(it) }
  }

  public fun overflows(rect: Rect2D): Boolean {
    return this.top < rect.top || rect.bottom < this.bottom || this.left < rect.left || rect.right < this.right
  }
}
