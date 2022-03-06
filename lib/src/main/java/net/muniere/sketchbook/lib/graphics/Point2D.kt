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

  public final data class Delta(
    public val x: Float = 0.0F,
    public val y: Float = 0.0F,
  )

  public final data class Partial(
    public val x: Float? = null,
    public val y: Float? = null,
  )

  public fun plus(
    x: Float = 0.0F,
    y: Float = 0.0F,
  ) = Point2D(
    x = this.x + x,
    y = this.y + y,
  )

  public fun plusAssign(
    x: Float = 0.0F,
    y: Float = 0.0F,
  ) {
    this.x += x
    this.y += y
  }

  public operator fun plus(delta: Delta): Point2D {
    return Point2D(
      x = this.x + delta.x,
      y = this.y + delta.y,
    )
  }

  public operator fun plusAssign(delta: Delta) {
    this.x += delta.x
    this.y += delta.y
  }

  public fun minus(
    x: Float = 0.0F,
    y: Float = 0.0F,
  ) = Point2D(
    x = this.x - x,
    y = this.y - y,
  )

  public fun minusAssign(
    x: Float = 0.0F,
    y: Float = 0.0F,
  ) {
    this.x -= x
    this.y -= y
  }

  public operator fun minus(delta: Delta): Point2D {
    return Point2D(
      x = this.x - delta.x,
      y = this.y - delta.y,
    )
  }

  public operator fun minusAssign(delta: Delta) {
    this.x -= delta.x
    this.y -= delta.y
  }

  public fun with(
    x: Float? = null,
    y: Float? = null,
  ) = Point2D(
    x = x ?: this.x,
    y = y ?: this.y,
  )

  public fun assign(
    x: Float? = null,
    y: Float? = null,
  ) {
    this.x = x ?: this.x
    this.y = y ?: this.y
  }

  public fun with(params: Partial): Point2D {
    return Point2D(
      x = params.x ?: this.x,
      y = params.y ?: this.y,
    )
  }

  public fun assign(params: Partial) {
    this.x = params.x ?: this.x
    this.y = params.y ?: this.y
  }

  public fun with(other: Point2D): Point2D {
    return Point2D(
      x = other.x,
      y = other.y,
    )
  }

  public fun assign(other: Point2D) {
    this.x = other.x
    this.y = other.y
  }
}
