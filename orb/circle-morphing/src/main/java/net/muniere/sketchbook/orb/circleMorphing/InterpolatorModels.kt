package net.muniere.sketchbook.orb.circleMorphing

import kotlin.math.pow

internal final object InterpolatorModels {

  internal final object Quadratic : InterpolatorModel() {
    override fun compute(fraction: Float): Float {
      return when (fraction < 0.5F) {
        true -> fraction.pow(2) * 2.0F
        false -> 1.0F - (1.0F - fraction).pow(2) * 2.0F
      }
    }
  }

  internal final object Cubic : InterpolatorModel() {
    override fun compute(fraction: Float): Float {
      return when (fraction < 0.5F) {
        true -> fraction.pow(3) * 4.0F
        false -> 1.0F - (1.0F - fraction).pow(3) * 4.0F
      }
    }
  }
}
