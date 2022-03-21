package net.muniere.sketchbook.lib.graphics

import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

public final data class Point2D(
  public var x: Float,
  public var y: Float,
) {
  public companion object {
    public fun zero() = Point2D(0.0F, 0.0F)

    public fun polar(radius: Float, angle: Float): Point2D {
      return Point2D(
        x = radius * cos(angle.toDouble()).toFloat(),
        y = radius * sin(angle.toDouble()).toFloat(),
      )
    }

    public fun dist(a: Point2D, b: Point2D): Float {
      return sqrt((a.x - b.x).pow(2) + (a.y - b.y).pow(2))
    }
  }

  public fun movingBy(x: Float = 0.0F, y: Float = 0.0F): Point2D {
    return Point2D(
      x = this.x + x,
      y = this.y + y,
    )
  }

  public operator fun plus(delta: Move2D): Point2D {
    return Point2D(
      x = this.x + delta.x,
      y = this.y + delta.y,
    )
  }

  public fun add(delta: Move2D) {
    this.x += delta.x
    this.y += delta.y
  }

  public operator fun minus(delta: Move2D): Point2D {
    return Point2D(
      x = this.x - delta.x,
      y = this.y - delta.y,
    )
  }

  public fun sub(delta: Move2D) {
    this.x -= delta.x
    this.y -= delta.y
  }

  public fun assign(other: Point2D) {
    this.x = other.x
    this.y = other.y
  }
}
