package net.muniere.sketchbook.lib

public final data class FloatRangeMapping(
  public val domain: FloatRange,
  public val target: FloatRange,
) {
  public fun apply(value: Float): Float {
    return this.target.lerp(this.domain.amountOf(value))
  }
}

public final data class DoubleRangeMapping(
  public val domain: DoubleRange,
  public val target: DoubleRange,
) {
  public fun apply(value: Double): Double {
    return this.target.lerp(this.domain.amountOf(value))
  }
}

public fun Float.map(domain: FloatRange, target: FloatRange): Float = target.lerp(domain.amountOf(this))

public fun Double.map(domain: DoubleRange, target: DoubleRange): Double = target.lerp(domain.amountOf(this))
