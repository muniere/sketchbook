package net.muniere.sketchbook.orb.fourierSeries

import net.muniere.sketchbook.lib.processing.FrameClock

internal final class ApplicationModel(
  public val clock: FrameClock,
  public val series: SeriesModel,
  public val path: PathModel,
) {

  public fun update() {
    this.series.update(this.clock)
    this.path.push(this.series.last().epicycleCenter().y)
  }
}
