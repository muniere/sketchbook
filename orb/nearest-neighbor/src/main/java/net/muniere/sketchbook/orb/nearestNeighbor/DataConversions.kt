package net.muniere.sketchbook.orb.nearestNeighbor

import net.muniere.sketchbook.orb.nearestNeighbor.domain.PersonModel
import net.muniere.sketchbook.orb.nearestNeighbor.domain.RatingModel
import net.muniere.sketchbook.orb.nearestNeighbor.io.PersonRecord

internal object DataConversions {

  internal fun decode(record: PersonRecord): PersonModel {
    return PersonModel(
      name = record.name,
      rating = RatingModel(
        I = record.I?.toDouble(),
        II = record.II?.toDouble(),
        III = record.III?.toDouble(),
        IV = record.IV?.toDouble(),
        V = record.V?.toDouble(),
        VI = record.VI?.toDouble(),
        VII = record.VII?.toDouble(),
        rogue1 = record.rogue1?.toDouble(),
        holiday = record.holiday?.toDouble(),
      )
    )
  }
}
