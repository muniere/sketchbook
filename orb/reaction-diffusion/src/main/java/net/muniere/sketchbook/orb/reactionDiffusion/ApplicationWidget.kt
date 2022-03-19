package net.muniere.sketchbook.orb.reactionDiffusion

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {

  private val grid = GridWidget(graphics)

  public var scale: Int
    get() = this.grid.scale
    set(value) = this.grid::scale.set(value)

  override fun doDraw(model: ApplicationModel) {
    this.grid.model = model.grid
    this.grid.draw()
  }
}
