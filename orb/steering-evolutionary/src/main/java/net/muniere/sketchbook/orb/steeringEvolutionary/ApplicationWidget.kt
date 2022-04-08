package net.muniere.sketchbook.orb.steeringEvolutionary

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  private val material = MaterialWidget(graphics)
  private val vehicle = VehicleWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    model.materials.forEach {
      this.material.model = it
      this.material.draw()
    }

    model.vehicles.forEach {
      this.vehicle.model = it
      this.vehicle.draw()
    }
  }
}
