package net.muniere.sketchbook.orb.travelingSalesperson.genetic

import net.muniere.sketchbook.orb.travelingSalesperson.PathModel

internal object MutationModels {

  internal final class Noise(
    public override val rate: Float,
    public val depth: Int,
  ) : MutationModel() {

    override fun perform(path: PathModel): PathModel {
      repeat(this.depth) {
        path.noise()
      }
      return path
    }
  }
}
