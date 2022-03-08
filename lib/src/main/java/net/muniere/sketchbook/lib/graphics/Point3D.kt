package net.muniere.sketchbook.lib.graphics

import kotlin.math.pow
import kotlin.math.sqrt

public final data class Point3D(
  public var x: Float,
  public var y: Float,
  public var z: Float,
) {
  public companion object {
    public fun zero() = Point3D(0.0F, 0.0F, 0.0F)

    public fun dist(a: Point3D, b: Point3D): Float {
      return sqrt((a.x - b.x).pow(2) + (a.y - b.y).pow(2) + (a.z - b.z).pow(2))
    }
  }

  public operator fun plus(delta: Move3D): Point3D {
    return Point3D(
      x = this.x + delta.x,
      y = this.y + delta.y,
      z = this.z + delta.z,
    )
  }

  public operator fun minus(delta: Move3D): Point3D {
    return Point3D(
      x = this.x - delta.x,
      y = this.y - delta.y,
      z = this.z - delta.z,
    )
  }

  public fun assign(other: Point3D) {
    this.x = other.x
    this.y = other.y
    this.z = other.z
  }
}
