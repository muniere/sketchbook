package net.muniere.sketchbook.orb.travelingSalesperson.genetic

import net.muniere.sketchbook.orb.travelingSalesperson.PathModel

internal abstract class MutationModel {
  abstract val rate: Float
  abstract fun perform(path: PathModel): PathModel
}
