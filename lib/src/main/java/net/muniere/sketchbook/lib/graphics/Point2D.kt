package net.muniere.sketchbook.lib.graphics

import kotlin.math.pow
import kotlin.math.sqrt

public final data class Point2D(
  public var x: Float,
  public var y: Float,
) {
  public companion object {
    public fun zero() = Point2D(0.0F, 0.0F)

    public fun dist(a: Point2D, b: Point2D): Float {
      return sqrt((a.x - b.x).pow(2) + (a.y - b.y).pow(2))
    }
  }

  public operator fun plus(delta: Move2D): Point2D {
    return Point2D(
      x = this.x + delta.x,
      y = this.y + delta.y,
    )
  }

  public operator fun minus(delta: Move2D): Point2D {
    return Point2D(
      x = this.x - delta.x,
      y = this.y - delta.y,
    )
  }

  public fun assign(other: Point2D) {
    this.x = other.x
    this.y = other.y
  }
}
