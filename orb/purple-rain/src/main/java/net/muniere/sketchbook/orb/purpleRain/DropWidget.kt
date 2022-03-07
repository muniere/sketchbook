package net.muniere.sketchbook.orb.purpleRain

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

public final class DropWidget(
  graphic: PGraphics,
) : ModelWidget<DropModel>(graphic) {

  override fun doDraw(model: DropModel) {
    this.scope {
      it.stroke(
        model.color.red(),
        model.color.green(),
        model.color.blue(),
      )
      it.line(
        model.point.x, model.point.y,
        model.point.x, model.point.y + model.length,
      )
    }
  }
}

