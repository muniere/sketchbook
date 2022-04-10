package net.muniere.sketchbook.orb.mitosisSimulation

import net.muniere.sketchbook.lib.graphics.circle
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class CellWidget(graphics: PGraphics) : ModelWidget<CellModel>(graphics) {

  override fun doDraw(model: CellModel) {
    this.scope {
      it.fill(model.fillColor)
      it.stroke(model.strokeColor)
      it.circle(model.center, model.radius * 2)
    }
  }
}
