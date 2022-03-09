package net.muniere.sketchbook.orb.circlePacking

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  private val circleCrowd = CircleCrowdWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    this.circleCrowd.model = model.circleCrowd
    this.circleCrowd.draw()
  }
}
