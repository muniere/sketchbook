package net.muniere.sketchbook.lib.graphics

public final data class Rect2D(
  public var origin: Point2D,
  public var size: Size2D,
) {
  public final data class Partial(
    public val origin: Point2D? = null,
    public val size: Size2D? = null,
  )

  public val top: Float
    get() = this.origin.y

  public val left: Float
    get() = this.origin.x

  public val right: Float
    get() = this.origin.x + this.size.width

  public val bottom: Float
    get() = this.origin.y + this.size.height

  public val width: Float
    get() = this.size.width

  public val height: Float
    get() = this.size.height

  public val center: Point2D
    get() = Point2D(
      x = this.origin.x + this.size.width / 2,
      y = this.origin.y + this.size.height / 2,
    )

  public fun with(
    origin: Point2D? = null,
    size: Size2D? = null,
  ): Rect2D {
    return Rect2D(
      origin = origin ?: this.origin.copy(),
      size = size ?: this.size.copy(),
    )
  }

  public fun assign(
    origin: Point2D? = null,
    size: Size2D? = null,
  ) {
    this.origin = origin ?: this.origin
    this.size = size ?: this.size
  }

  public fun with(params: Partial): Rect2D {
    return Rect2D(
      origin = params.origin ?: this.origin.copy(),
      size = params.size ?: this.size.copy(),
    )
  }

  public fun assign(params: Partial) {
    this.origin = params.origin ?: this.origin
    this.size = params.size ?: this.size
  }

  public fun with(other: Rect2D): Rect2D {
    return Rect2D(
      origin = other.origin,
      size = other.size,
    )
  }

  public fun assign(other: Rect2D) {
    this.origin = other.origin
    this.size = other.size
  }

  public fun contains(point: Point2D): Boolean {
    return this.left <= point.x && point.x < this.right && this.top <= point.y && point.y < this.bottom;
  }

  public fun intersects(other: Rect2D): Boolean {
    return (this.left <= other.right && other.left <= this.right) && (this.top <= other.bottom && other.top <= this.bottom);
  }

}
