package net.muniere.sketchbook.orb.nearestNeighbor.domain

import kotlin.math.pow
import kotlin.math.sqrt

internal data class RatingModel(
  internal val I: Double? = null,
  internal val II: Double? = null,
  internal val III: Double? = null,
  internal val IV: Double? = null,
  internal val V: Double? = null,
  internal val VI: Double? = null,
  internal val VII: Double? = null,
  internal val rogue1: Double? = null,
  internal val holiday: Double? = null,
) {

  companion object {
    public fun similarity(a: RatingModel, b: RatingModel): Double {
      return 1 / (distance(a, b) + 1)
    }

    public fun distance(a: RatingModel, b: RatingModel): Double {
      val pairs = listOf(
        a.I to b.I,
        a.II to b.II,
        a.III to b.III,
        a.IV to b.IV,
        a.V to b.V,
        a.VI to b.VI,
        a.VII to b.VII,
        a.rogue1 to b.rogue1,
        a.holiday to b.holiday,
      ).mapNotNull { (va, vb) ->
        when {
          va != null && vb != null -> va to vb
          else -> null
        }
      }

      return sqrt(pairs.sumOf { (it.first - it.second).pow(2) })
    }
  }
}
