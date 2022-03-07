package net.muniere.sketchbook.orb.mengerSponge

import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import processing.core.PGraphics

public final class CubeWidget(graphics: PGraphics) : ModelWidget<CubeModel>(graphics) {

  override fun doDraw(model: CubeModel) {
    this.scope {
      it.translate(model.center.x, model.center.y, model.center.z)
      it.fill(model.color)
      it.box(model.size)
    }
  }
}
