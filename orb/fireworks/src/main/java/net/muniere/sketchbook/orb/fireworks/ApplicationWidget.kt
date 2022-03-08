package net.muniere.sketchbook.orb.fireworks

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  private val firework: FireworkWidget = FireworkWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    model.fireworks.forEach {
      this.firework.model = it
      this.firework.draw()
    }
  }
}
