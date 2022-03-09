package net.muniere.sketchbook.lib

import kotlin.random.Random

public class FloatRange(
  override val start: Float,
  override val endInclusive: Float,
) : ClosedFloatingPointRange<Float> {
  override fun lessThanOrEquals(a: Float, b: Float): Boolean {
    return a <= b
  }

  override fun contains(value: Float): Boolean {
    return value in this.start..this.endInclusive
  }

  override fun isEmpty(): Boolean {
    return this.start > this.endInclusive
  }

  override fun equals(other: Any?): Boolean {
    return other is FloatRange && (isEmpty() && other.isEmpty() ||
      this.start == other.start && this.endInclusive == other.endInclusive)
  }

  override fun hashCode(): Int {
    return if (isEmpty()) -1 else 31 * this.start.hashCode() + this.endInclusive.hashCode()
  }

  public fun random(): Float {
    return this.start + Random.nextFloat() * (this.endInclusive - this.start)
  }

  public fun lerp(amount: Float): Float {
    return this.start + (this.endInclusive - this.start) * amount
  }

  public fun amountOf(value: Float): Float {
    return (value - this.start) / (this.endInclusive - this.start)
  }
}

public class DoubleRange(
  override val start: Double,
  override val endInclusive: Double,
) : ClosedFloatingPointRange<Double> {
  override fun lessThanOrEquals(a: Double, b: Double): Boolean {
    return a <= b
  }

  override fun contains(value: Double): Boolean {
    return value in this.start..this.endInclusive
  }

  override fun isEmpty(): Boolean {
    return this.start > this.endInclusive
  }

  override fun equals(other: Any?): Boolean {
    return other is DoubleRange && (isEmpty() && other.isEmpty() ||
      this.start == other.start && this.endInclusive == other.endInclusive)
  }

  override fun hashCode(): Int {
    return if (isEmpty()) -1 else 31 * this.start.hashCode() + this.endInclusive.hashCode()
  }

  public fun random(): Double {
    return this.start + Random.nextDouble() * (this.endInclusive - this.start)
  }

  public fun lerp(amount: Double): Double {
    return this.start + (this.endInclusive - this.start) * amount
  }

  public fun amountOf(value: Double): Double {
    return (value - this.start) / (this.endInclusive - this.start)
  }
}
