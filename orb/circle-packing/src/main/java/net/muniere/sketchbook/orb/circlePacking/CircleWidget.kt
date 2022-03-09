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

      when (val color = model.strokeColor) {
        null -> it.noStroke()
        else -> it.stroke(color)
      }
      when (val color = model.fillColor) {
        null -> it.noFill()
        else -> it.fill(color)
      }

      it.circle(model.center, model.radius * 2)
    }
  }
}
