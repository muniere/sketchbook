package net.muniere.sketchbook.orb.reactionDiffusion

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {

  private val grid = GridWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    this.grid.model = model.grid
    this.grid.draw()
  }
}
