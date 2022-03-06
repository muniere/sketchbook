package net.muniere.sketchbook.lib.graphics

public final data class Size2D(
  public var width: Float,
  public var height: Float,
) {
  public companion object {
    public fun zero() = Size2D(0.0F, 0.0F)
    public fun square(size: Float) = Size2D(size, size)
  }

  public final data class Delta(
    public val width: Float = 0.0F,
    public val height: Float = 0.0F,
  )

  public final data class Partial(
    public val width: Float? = null,
    public val height: Float? = null,
  )

  public fun plus(
    width: Float = 0.0F,
    height: Float = 0.0F,
  ) = Size2D(
    width = this.width + width,
    height = this.height + height,
  )

  public fun plusAssign(
    width: Float = 0.0F,
    height: Float = 0.0F,
  ) {
    this.width += width
    this.height += height
  }

  public operator fun plus(delta: Delta): Size2D {
    return Size2D(
      width = this.width + delta.width,
      height = this.height + delta.height,
    )
  }

  public operator fun plusAssign(delta: Delta) {
    this.width += delta.width
    this.height += delta.height
  }

  public fun minus(
    width: Float = 0.0F,
    height: Float = 0.0F,
  ) = Size2D(
    width = this.width - width,
    height = this.height - height,
  )

  public fun minusAssign(
    width: Float = 0.0F,
    height: Float = 0.0F,
  ) {
    this.width -= width
    this.height -= height
  }

  public operator fun minus(delta: Delta): Size2D {
    return Size2D(
      width = this.width - delta.width,
      height = this.height - delta.height,
    )
  }

  public operator fun minusAssign(delta: Delta) {
    this.width -= delta.width
    this.height -= delta.height
  }

  public operator fun times(value: Float): Size2D {
    return Size2D(
      width = this.width * value,
      height = this.height * value,
    )
  }

  public operator fun timesAssign(value: Float) {
    this.width *= value
    this.height *= value
  }

  public fun with(
    width: Float? = null,
    height: Float? = null,
  ) = Size2D(
    width = width ?: this.width,
    height = height ?: this.height,
  )

  public fun assign(
    width: Float? = null,
    height: Float? = null,
  ) {
    this.width = width ?: this.width
    this.height = height ?: this.height
  }

  public fun with(params: Partial): Size2D {
    return Size2D(
      width = params.width ?: this.width,
      height = params.height ?: this.height,
    )
  }

  public fun assign(params: Partial) {
    this.width = params.width ?: this.width
    this.height = params.height ?: this.height
  }

  public fun with(other: Size2D): Size2D {
    return Size2D(
      width = other.width,
      height = other.height,
    )
  }

  public fun assign(other: Size2D) {
    this.width = other.width
    this.height = other.height
  }
}
