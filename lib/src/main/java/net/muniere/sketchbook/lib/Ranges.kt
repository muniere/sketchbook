package net.muniere.sketchbook.lib

import kotlin.random.Random

public typealias FloatRange = ClosedFloatingPointRange<Float>

public fun FloatRange.random(): Float {
  return this.start + Random.nextFloat() * (this.endInclusive - this.start)
}

public fun FloatRange.lerp(amount: Float): Float {
  return this.start + (this.endInclusive - this.start) * amount
}

public fun FloatRange.amountOf(value: Float): Float {
  return (value - this.start) / (this.endInclusive - this.start)
}

public typealias DoubleRange = ClosedFloatingPointRange<Double>

public fun DoubleRange.random(): Double {
  return this.start + Random.nextDouble() * (this.endInclusive - this.start)
}

public fun DoubleRange.lerp(amount: Double): Double {
  return this.start + (this.endInclusive - this.start) * amount
}

public fun DoubleRange.amountOf(value: Double): Double {
  return (value - this.start) / (this.endInclusive - this.start)
}
