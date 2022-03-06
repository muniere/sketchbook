package net.muniere.sketchbook.orb.starField

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  private val starField: StarFieldWidget = StarFieldWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    this.starField.model = model.starField
    this.starField.draw()
  }
}
