package net.muniere.sketchbook.lib.graphics

public final data class Rect3D(
  public var origin: Point3D,
  public var size: Size3D,
) {
  public final data class Partial(
    public val origin: Point3D? = null,
    public val size: Size3D? = null,
  )

  public val width: Float
    get() = this.size.width

  public val height: Float
    get() = this.size.height

  public val depth: Float
    get() = this.size.depth

  public fun with(
    origin: Point3D? = null,
    size: Size3D? = null,
  ): Rect3D {
    return Rect3D(
      origin = origin ?: this.origin.copy(),
      size = size ?: this.size.copy(),
    )
  }

  public fun assign(
    origin: Point3D? = null,
    size: Size3D? = null,
  ) {
    this.origin = origin ?: this.origin
    this.size = size ?: this.size
  }

  public fun with(params: Partial): Rect3D {
    return Rect3D(
      origin = params.origin ?: this.origin.copy(),
      size = params.size ?: this.size.copy(),
    )
  }

  public fun assign(params: Partial) {
    this.origin = params.origin ?: this.origin
    this.size = params.size ?: this.size
  }

  public fun with(other: Rect3D): Rect3D {
    return Rect3D(
      origin = other.origin,
      size = other.size,
    )
  }

  public fun assign(other: Rect3D) {
    this.origin = other.origin
    this.size = other.size
  }
}
