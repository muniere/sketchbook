package net.muniere.sketchbook.orb.steeringBehaviors

import net.muniere.sketchbook.lib.graphics.circle
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class VehicleWidget(graphics: PGraphics) : ModelWidget<VehicleModel>(graphics) {

  override fun doDraw(model: VehicleModel) {
    this.scope {
      it.fill(model.fillColor)
      it.stroke(model.strokeColor)
      it.circle(model.center, model.radius * 2)
    }
  }
}
