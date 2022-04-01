package net.muniere.sketchbook.lib.graphics

public final data class Size2D(
  public var width: Float,
  public var height: Float,
) {
  public companion object {
    public fun zero() = Size2D(0.0F, 0.0F)
    public fun square(size: Float) = Size2D(size, size)
  }

  public operator fun plus(delta: Size2D): Size2D {
    return Size2D(
      width = this.width + delta.width,
      height = this.height + delta.height,
    )
  }

  public fun add(delta: Size2D) {
    this.width += delta.width
    this.height += delta.height
  }

  public operator fun minus(delta: Size2D): Size2D {
    return Size2D(
      width = this.width - delta.width,
      height = this.height - delta.height,
    )
  }

  public fun sub(delta: Size2D) {
    this.width -= delta.width
    this.height -= delta.height
  }

  public operator fun times(value: Float): Size2D {
    return Size2D(
      width = this.width * value,
      height = this.height * value,
    )
  }

  public fun mult(value: Float) {
    this.width *= value
    this.height *= value
  }

  public operator fun div(value: Float): Size2D {
    return Size2D(
      width = this.width / value,
      height = this.height / value,
    )
  }

  public fun assign(other: Size2D) {
    this.width = other.width
    this.height = other.height
  }
}
