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

  public operator fun plus(delta: Size3D): Size3D {
    return Size3D(
      width = this.width + delta.width,
      height = this.height + delta.height,
      depth = this.depth + delta.depth,
    )
  }

  public fun add(delta: Size3D) {
    this.width += delta.width
    this.height += delta.height
    this.depth += delta.depth
  }

  public operator fun minus(delta: Size3D): Size3D {
    return Size3D(
      width = this.width - delta.width,
      height = this.height - delta.height,
      depth = this.depth - delta.depth,
    )
  }

  public fun sub(delta: Size3D) {
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

  public fun mult(value: Float) {
    this.width *= value
    this.height *= value
    this.depth *= value
  }

  public fun assign(other: Size3D) {
    this.width = other.width
    this.height = other.height
    this.depth = other.depth
  }
}
