package net.muniere.sketchbook.orb.circleMorphing

import kotlin.math.pow

internal abstract class InterpolatorModel {
  abstract fun compute(fraction: Float): Float
}
