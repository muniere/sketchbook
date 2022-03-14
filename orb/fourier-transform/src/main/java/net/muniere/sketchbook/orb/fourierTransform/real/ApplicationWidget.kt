package net.muniere.sketchbook.orb.fourierTransform.real

import net.muniere.sketchbook.lib.graphics.Line2D
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.translate
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.orb.fourierTransform.LineWidget
import net.muniere.sketchbook.orb.fourierTransform.PathWidget
import net.muniere.sketchbook.orb.fourierTransform.SeriesWidget
import processing.core.PGraphics

internal final class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  public var origin = Point2D.zero()

  public val xSeries = SeriesWidget(graphics)
  public val ySeries = SeriesWidget(graphics)
  public val xLine = LineWidget(graphics)
  public val yLine = LineWidget(graphics)
  public val path = PathWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    if (model.path.isEmpty()) {
      return
    }

    this.xSeries.model = model.xSeries
    this.ySeries.model = model.ySeries
    this.path.model = model.path

    this.xLine.model = Line2D(
      start = model.xSeries.last().epicycleCenter(),
      stop = model.path.last(),
    )
    this.yLine.model = Line2D(
      start = model.ySeries.last().epicycleCenter(),
      stop = model.path.last(),
    )

    this.scope {
      it.translate(this.origin)

      this.xSeries.draw()
      this.ySeries.draw()
      this.xLine.draw()
      this.yLine.draw()
      this.path.draw()
    }
  }
}
