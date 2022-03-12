package net.muniere.sketchbook.orb.circlePacking

import net.muniere.sketchbook.lib.graphics.circle
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class CircleWidget(graphics: PGraphics) : ModelWidget<CircleModel>(graphics) {

  override fun doDraw(model: CircleModel) {
    this.scope {
      it.strokeWeight(model.strokeWeight)
      it.stroke(model.strokeColor)
      it.fill(model.fillColor)
      it.circle(model.center, model.radius * 2)
    }
  }
}
