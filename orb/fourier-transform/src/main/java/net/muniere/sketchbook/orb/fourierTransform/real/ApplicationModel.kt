package net.muniere.sketchbook.orb.fourierTransform.real

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.processing.FrameClock
import net.muniere.sketchbook.orb.fourierTransform.PathModel
import net.muniere.sketchbook.orb.fourierTransform.SeriesModel

internal final class ApplicationModel(
  public val clock: FrameClock,
  public val xSeries: SeriesModel,
  public val ySeries: SeriesModel,
  public val path: PathModel,
) {

  public fun update() {
    this.xSeries.update(
      clock = this.clock,
      offset = 0.0F,
    )
    this.ySeries.update(
      clock = this.clock,
      offset = (Math.PI / 2).toFloat(),
    )

    val nextPoint = Point2D(
      x = this.xSeries.last().epicycleCenter().x,
      y = this.ySeries.last().epicycleCenter().y,
    )

    this.path.push(nextPoint)
  }
}
