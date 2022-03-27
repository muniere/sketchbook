package net.muniere.sketchbook.lib

import android.graphics.PathMeasure

public fun PathMeasure.sample(step: Float): List<FloatArray> {
  val positions = mutableListOf<FloatArray>()

  do {
    FloatRange(0.0F, this.length).sequence(step).forEach {
      val pos = FloatArray(2)
      this.getPosTan(it, pos, null)
      positions.add(pos)
    }
  } while (this.nextContour())

  return positions
}
