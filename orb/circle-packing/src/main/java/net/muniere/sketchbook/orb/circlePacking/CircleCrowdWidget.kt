package net.muniere.sketchbook.orb.circlePacking

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class CircleCrowdWidget(graphics: PGraphics) : ModelWidget<CircleCrowdModel>(graphics) {
  private val circle = CircleWidget(graphics)

  override fun doDraw(model: CircleCrowdModel) {
    model.circles.forEach {
      this.circle.model = it
      this.circle.draw()
    }
  }
}
