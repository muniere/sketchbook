package net.muniere.sketchbook.lib.math

public final data class Dimen(
  public val width: Int,
  public val height: Int,
) {
  public companion object {
    public fun zero() = Dimen(0, 0)
    public fun square(size: Int) = Dimen(size, size)
  }
}
