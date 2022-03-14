package net.muniere.sketchbook.lib.graphics

public final data class Move2D(
  public var x: Float = 0.0F,
  public var y: Float = 0.0F,
) {
  public companion object {
    public fun zero() = Move2D(0.0F, 0.0F)
  }

  public operator fun plus(delta: Move2D): Move2D {
    return Move2D(
      x = this.x + delta.x,
      y = this.y + delta.y,
    )
  }

  public operator fun minus(delta: Move2D): Move2D {
    return Move2D(
      x = this.x - delta.x,
      y = this.y - delta.y,
    )
  }

  public operator fun times(value: Float): Move2D {
    return Move2D(
      x = this.x * value,
      y = this.y * value,
    )
  }
}
