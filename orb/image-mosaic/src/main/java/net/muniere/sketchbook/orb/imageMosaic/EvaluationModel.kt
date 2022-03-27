package net.muniere.sketchbook.orb.imageMosaic

import processing.core.PImage

internal abstract class EvaluationModel {
  public abstract val minValue: Value
  public abstract val maxValue: Value
  public abstract fun apply(pixel: Int): Value

  public val range: IntRange
    get() = IntRange(this.minValue, this.maxValue)

  public fun apply(image: PImage): Value {
    val total = image.pixels.sumOf { this.apply(it) }.toDouble()
    val average = total / (image.width * image.height)
    return average.toInt()
  }
}
