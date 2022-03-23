package net.muniere.sketchbook.orb.imageMosaic

import processing.core.PGraphics

internal object EvaluationModels {

  internal final class Alpha(
    private val graphics: PGraphics,
  ) : EvaluationModel() {

    override val minValue: Value
      get() = 0

    override val maxValue: Value
      get() = 255

    override fun apply(pixel: Int): Value {
      return this.graphics.alpha(pixel).toInt()
    }
  }

  internal final class Brightness(
    private val graphics: PGraphics,
  ) : EvaluationModel() {

    override val minValue: Int
      get() = 0

    override val maxValue: Int
      get() = 255

    override fun apply(pixel: Int): Value {
      return this.graphics.brightness(pixel).toInt()
    }
  }

  internal final class Average(
    private val graphics: PGraphics,
  ) : EvaluationModel() {

    override val minValue: Int
      get() = 0

    override val maxValue: Int
      get() = 255

    override fun apply(pixel: Int): Value {
      val r = pixel shr 16
      val g = pixel shr 8
      val b = pixel shr 0
      return (r + g + b) / 3
    }
  }
}
