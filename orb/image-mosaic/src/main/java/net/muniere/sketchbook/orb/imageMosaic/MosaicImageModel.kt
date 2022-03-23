package net.muniere.sketchbook.orb.imageMosaic

import net.muniere.sketchbook.lib.math.Matrix
import net.muniere.sketchbook.lib.math.Spot

internal final class MosaicImageModel(
  private val patches: Matrix<PatchImageModel>,
) {
  public val width: Int
    get() = this.patches.width

  public val height: Int
    get() = this.patches.height

  public fun get(spot: Spot): PatchImageModel {
    return this.patches.get(spot)
  }

  public fun forEachIndexed(action: (value: PatchImageModel, spot: Spot) -> Unit) {
    this.patches.forEachIndexed(action)
  }
}
