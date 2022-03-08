package net.muniere.sketchbook.orb.purpleRain

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class ApplicationWidget(
  graphics: PGraphics,
) : ModelWidget<ApplicationModel>(graphics) {

  private val drop: DropWidget = DropWidget(this.g)

  override fun doDraw(model: ApplicationModel) {
    model.drops.forEach {
      this.drop.model = it
      this.drop.draw()
    }
  }
}
