package net.muniere.sketchbook.orb.steeringBehaviors

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  private val vehicle = VehicleWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    model.vehicles.forEach {
      this.vehicle.model = it
      this.vehicle.draw()
    }
  }
}
