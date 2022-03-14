package net.muniere.sketchbook.orb.fourierTransform

import net.muniere.sketchbook.lib.math.Complex
import kotlin.math.atan2

internal final data class CircleSeed(
  public val frequency: Float,
  public val amplitude: Float,
  public val phase: Float,
) {

  public companion object {
    public fun create(k: Int, X: Complex): CircleSeed {
      return CircleSeed(
        frequency = k.toFloat(),
        amplitude = X.norm().toFloat(),
        phase = atan2(X.im, X.re).toFloat(),
      )
    }
  }
}
