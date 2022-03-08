package net.muniere.sketchbook.orb.mengerSponge

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  private val sponge: SpongeWidget = SpongeWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    this.sponge.model = model.sponge
    this.sponge.draw()
  }
}
