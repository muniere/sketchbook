package net.muniere.sketchbook.orb.fourierTransform.complex

import net.muniere.sketchbook.lib.processing.FrameClock
import net.muniere.sketchbook.orb.fourierTransform.PathModel
import net.muniere.sketchbook.orb.fourierTransform.SeriesModel

internal final class ApplicationModel(
  public val clock: FrameClock,
  public val chain: SeriesModel,
  public val path: PathModel,
) {

  public fun update() {
    this.chain.update(
      clock = this.clock,
      offset = 0.0F,
    )

    val nextPoint = this.chain.last().epicycleCenter()

    this.path.push(nextPoint)
  }
}
