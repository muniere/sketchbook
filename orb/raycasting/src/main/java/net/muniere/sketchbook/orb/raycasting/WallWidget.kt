package net.muniere.sketchbook.orb.raycasting

import net.muniere.sketchbook.lib.graphics.line
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class WallWidget(graphics: PGraphics) : ModelWidget<WallModel>(graphics) {

  override fun doDraw(model: WallModel) {
    this.scope {
      it.stroke(model.color)
      it.line(model.p1, model.p2)
    }
  }
}
