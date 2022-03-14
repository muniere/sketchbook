package net.muniere.sketchbook.orb.fourierSeries

import net.muniere.sketchbook.lib.graphics.Line2D
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  public var origin = Point2D.zero()

  public val series = SeriesWidget(graphics)
  public val path = PathWidget(graphics)
  public val line = LineWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    val start = model.series.last().epicycleCenter()
    val end = this.path.origin.copy(y = start.y)

    this.series.model = model.series
    this.path.model = model.path
    this.line.model = Line2D(start, end)

    this.scope {
      it.translate(this.origin.x, this.origin.y)

      this.series.draw()
      this.path.draw()
      this.line.draw()
    }
  }
}
