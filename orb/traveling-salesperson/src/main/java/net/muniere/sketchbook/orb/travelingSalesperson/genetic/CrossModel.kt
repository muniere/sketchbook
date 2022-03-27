package net.muniere.sketchbook.orb.travelingSalesperson.genetic

import net.muniere.sketchbook.orb.travelingSalesperson.PathModel

internal abstract class CrossModel {
  abstract fun perform(a: PathModel, b: PathModel): PathModel
}
