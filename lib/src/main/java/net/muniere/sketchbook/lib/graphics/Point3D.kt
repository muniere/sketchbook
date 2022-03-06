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

  public final data class Delta(
    public val x: Float = 0.0F,
    public val y: Float = 0.0F,
    public val z: Float = 0.0F,
  )

  public final data class Partial(
    public val x: Float? = null,
    public val y: Float? = null,
    public val z: Float? = null,
  )

  public fun plus(
    x: Float = 0.0F,
    y: Float = 0.0F,
    z: Float = 0.0F,
  ) = Point3D(
    x = this.x + x,
    y = this.y + y,
    z = this.z + z,
  )

  public fun plusAssign(
    x: Float = 0.0F,
    y: Float = 0.0F,
    z: Float = 0.0F
  ) {
    this.x += x
    this.y += y
    this.z += z
  }

  public operator fun plus(delta: Delta): Point3D {
    return Point3D(
      x = this.x + delta.x,
      y = this.y + delta.y,
      z = this.z + delta.z,
    )
  }

  public operator fun plusAssign(delta: Delta) {
    this.x += delta.x
    this.y += delta.y
    this.z += delta.z
  }

  public fun minus(
    x: Float = 0.0F,
    y: Float = 0.0F,
    z: Float = 0.0F,
  ) = Point3D(
    x = this.x - x,
    y = this.y - y,
    z = this.z - z,
  )

  public fun minusAssign(
    x: Float = 0.0F,
    y: Float = 0.0F,
    z: Float = 0.0F,
  ) {
    this.x -= x
    this.y -= y
    this.z -= z
  }

  public operator fun minus(delta: Delta): Point3D {
    return Point3D(
      x = this.x - delta.x,
      y = this.y - delta.y,
      z = this.z - delta.z,
    )
  }

  public operator fun minusAssign(delta: Delta) {
    this.x -= delta.x
    this.y -= delta.y
    this.z -= delta.z
  }

  public fun with(
    x: Float? = null,
    y: Float? = null,
    z: Float? = null,
  ) = Point3D(
    x = x ?: this.x,
    y = y ?: this.y,
    z = z ?: this.z,
  )

  public fun assign(
    x: Float? = null,
    y: Float? = null,
    z: Float? = null,
  ) {
    this.x = x ?: this.x
    this.y = y ?: this.y
    this.z = z ?: this.z
  }

  public fun with(params: Partial): Point3D {
    return Point3D(
      x = params.x ?: this.x,
      y = params.y ?: this.y,
      z = params.z ?: this.z,
    )
  }

  public fun assign(params: Partial) {
    this.x = params.x ?: this.x
    this.y = params.y ?: this.y
    this.z = params.z ?: this.z
  }

  public fun with(other: Point3D): Point3D {
    return Point3D(
      x = other.x,
      y = other.y,
      z = other.z,
    )
  }

  public fun assign(other: Point3D) {
    this.x = other.x
    this.y = other.y
    this.z = other.z
  }
}
