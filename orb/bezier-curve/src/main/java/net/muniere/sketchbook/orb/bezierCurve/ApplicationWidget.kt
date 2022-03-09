package net.muniere.sketchbook.orb.bezierCurve

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  private val point = VehicleWidget(graphics)
  private val bezier = PathWidget(graphics)

  public var components: List<PathWidget.Component>
    get() = this.bezier.components
    set(value) = this.bezier::components.set(value)

  override fun doDraw(model: ApplicationModel) {
    this.bezier.model = model.bezier
    this.bezier.draw()

    model.vehicles.forEach {
      this.point.model = it
      this.point.draw()
    }
  }
}
