package net.muniere.sketchbook.orb.steeringBehaviors

import net.muniere.sketchbook.lib.graphics.circle
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class VehicleWidget(graphics: PGraphics) : ModelWidget<VehicleModel>(graphics) {

  override fun doDraw(model: VehicleModel) {
    this.scope {
      when (val color = model.fillColor) {
        null -> it.noFill()
        else -> it.fill(color)
      }
      when (val color = model.strokeColor) {
        null -> it.noStroke()
        else -> it.stroke(color)
      }

      it.circle(model.center, model.radius * 2)
    }
  }
}
