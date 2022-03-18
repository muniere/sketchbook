package net.muniere.sketchbook.orb.nearestNeighbor

import net.muniere.sketchbook.orb.nearestNeighbor.domain.RatingModel
import net.muniere.sketchbook.orb.nearestNeighbor.domain.PersonModel

internal final data class FormState(
  internal val I: Int? = null,
  internal val II: Int? = null,
  internal val III: Int? = null,
  internal val IV: Int? = null,
  internal val V: Int? = null,
  internal val VI: Int? = null,
  internal val VII: Int? = null,
  internal val rogue1: Int? = null,
  internal val holiday: Int? = null,
) {
  public fun toPerson(): PersonModel {
    return PersonModel(
      name = "anonymous",
      rating = RatingModel(
        I = this.I?.toDouble(),
        II = this.II?.toDouble(),
        III = this.III?.toDouble(),
        IV = this.IV?.toDouble(),
        V = this.V?.toDouble(),
        VI = this.VI?.toDouble(),
        VII = this.VII?.toDouble(),
        rogue1 = this.rogue1?.toDouble(),
        holiday = this.holiday?.toDouble(),
      )
    )
  }
}

