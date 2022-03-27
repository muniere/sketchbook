package net.muniere.sketchbook.lib.math

public final data class Fraction(
  public val numerator: Int,
  public val denominator: Int,
) {
  public fun toFloat(): Float {
    return this.numerator.toFloat() / this.denominator
  }

  public fun toDouble(): Double {
    return this.numerator.toDouble() / this.denominator
  }
}
