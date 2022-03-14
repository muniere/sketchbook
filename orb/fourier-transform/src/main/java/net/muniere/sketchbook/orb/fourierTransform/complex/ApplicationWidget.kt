package net.muniere.sketchbook.orb.fourierTransform.complex

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.translate
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.orb.fourierTransform.PathWidget
import net.muniere.sketchbook.orb.fourierTransform.SeriesWidget
import processing.core.PGraphics

internal final class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  public var origin = Point2D.zero()

  public val chain = SeriesWidget(graphics)
  public val path = PathWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    if (model.path.isEmpty()) {
      return
    }

    this.chain.model = model.chain
    this.path.model = model.path

    this.scope {
      it.translate(this.origin)

      this.chain.draw()
      this.path.draw()
    }
  }
}
