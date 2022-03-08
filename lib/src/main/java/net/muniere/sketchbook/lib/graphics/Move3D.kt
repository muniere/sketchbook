package net.muniere.sketchbook.lib.graphics

public final data class Move3D(
  public var x: Float,
  public var y: Float,
  public var z: Float,
) {
  public companion object {
    public fun zero() = Move3D(0.0F, 0.0F, 0.0F)
  }

  public operator fun plus(delta: Move3D): Move3D {
    return Move3D(
      x = this.x + delta.x,
      y = this.y + delta.y,
      z = this.z + delta.z,
    )
  }

  public operator fun minus(delta: Move3D): Move3D {
    return Move3D(
      x = this.x - delta.x,
      y = this.y - delta.y,
      z = this.z - delta.z,
    )
  }

  public operator fun times(value: Float): Move3D {
    return Move3D(
      x = this.x * value,
      y = this.y * value,
      z = this.z * value,
    )
  }
}
