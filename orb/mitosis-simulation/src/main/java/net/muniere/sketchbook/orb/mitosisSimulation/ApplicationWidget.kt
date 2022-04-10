package net.muniere.sketchbook.orb.mitosisSimulation

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  private val cell = CellWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    model.cells.reversed().forEach {
      this.cell.model = it
      this.cell.draw()
    }
  }
}
