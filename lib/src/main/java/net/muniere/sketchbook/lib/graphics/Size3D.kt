package net.muniere.sketchbook.lib.graphics

public final data class Size3D(
  public var width: Float,
  public var height: Float,
  public var depth: Float,
) {
  public companion object {
    public fun zero() = Size3D(0.0F, 0.0F, 0.0F)
    public fun cube(size: Float) = Size3D(size, size, size)
  }

  public final data class Delta(
    public val width: Float = 0.0F,
    public val height: Float = 0.0F,
    public val depth: Float = 0.0F,
  )

  public final data class Partial(
    public val width: Float? = null,
    public val height: Float? = null,
    public val depth: Float? = null,
  )

  public fun plus(
    width: Float = 0.0F,
    height: Float = 0.0F,
    depth: Float = 0.0F,
  ) = Size3D(
    width = this.width + width,
    height = this.height + height,
    depth = this.depth + depth,
  )

  public fun plusAssign(
    width: Float = 0.0F,
    height: Float = 0.0F,
    depth: Float = 0.0F,
  ) {
    this.width += width
    this.height += height
    this.depth += depth
  }

  public operator fun plus(delta: Delta): Size3D {
    return Size3D(
      width = this.width + delta.width,
      height = this.height + delta.height,
      depth = this.depth + delta.depth,
    )
  }

  public operator fun plusAssign(delta: Delta) {
    this.width += delta.width
    this.height += delta.height
    this.depth += delta.depth
  }

  public fun minus(
    width: Float = 0.0F,
    height: Float = 0.0F,
    depth: Float = 0.0F,
  ) = Size3D(
    width = this.width - width,
    height = this.height - height,
    depth = this.depth - depth,
  )

  public fun minusAssign(
    width: Float = 0.0F,
    height: Float = 0.0F,
    depth: Float = 0.0F,
  ) {
    this.width -= width
    this.height -= height
    this.depth -= depth
  }

  public operator fun minus(delta: Delta): Size3D {
    return Size3D(
      width = this.width - delta.width,
      height = this.height - delta.height,
      depth = this.depth - delta.depth,
    )
  }

  public operator fun minusAssign(delta: Delta) {
    this.width -= delta.width
    this.height -= delta.height
    this.depth -= delta.depth
  }

  public operator fun times(value: Float): Size3D {
    return Size3D(
      width = this.width * value,
      height = this.height * value,
      depth = this.depth * value,
    )
  }

  public operator fun timesAssign(value: Float) {
    this.width *= value
    this.height *= value
    this.depth *= value
  }

  public fun with(
    width: Float? = null,
    height: Float? = null,
    depth: Float? = null,
  ) = Size3D(
    width = width ?: this.width,
    height = height ?: this.height,
    depth = depth ?: this.depth,
  )

  public fun assign(
    width: Float? = null,
    height: Float? = null,
    depth: Float? = null,
  ) {
    this.width = width ?: this.width
    this.height = height ?: this.height
    this.depth = depth ?: this.depth
  }

  public fun with(params: Partial): Size3D {
    return Size3D(
      width = params.width ?: this.width,
      height = params.height ?: this.height,
      depth = params.depth ?: this.depth,
    )
  }

  public fun assign(params: Partial) {
    this.width = params.width ?: this.width
    this.height = params.height ?: this.height
    this.depth = params.depth ?: this.depth
  }

  public fun with(other: Size3D): Size3D {
    return Size3D(
      width = other.width,
      height = other.height,
      depth = other.depth,
    )
  }

  public fun assign(other: Size3D) {
    this.width = other.width
    this.height = other.height
    this.depth = other.depth
  }
}
