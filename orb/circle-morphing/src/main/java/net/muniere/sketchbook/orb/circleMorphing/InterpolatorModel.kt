package net.muniere.sketchbook.orb.circleMorphing

import kotlin.math.pow

internal fun interface InterpolatorModel {
  public fun compute(fraction: Float): Float
}

internal final class DefaultInterpolatorModel : InterpolatorModel {
  override fun compute(fraction: Float): Float {
    return when (fraction < 0.5F) {
      true -> fraction.pow(2) * 2.0F
      false -> 1.0F - (1.0F - fraction).pow(2) * 2.0F
    }
  }
}
