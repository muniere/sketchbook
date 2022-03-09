package net.muniere.sketchbook.lib

public fun generateSequence(start: Float, end: Float = Float.MAX_VALUE, step: Float = 1.0F): Sequence<Float> {
  return generateSequence(start) { prev -> (prev + step).takeIf { it < end } }
}

public fun generateSequence(start: Double, end: Double = Double.MAX_VALUE, step: Double = 1.0): Sequence<Double> {
  return generateSequence(start) { prev -> (prev + step).takeIf { it < end } }
}
