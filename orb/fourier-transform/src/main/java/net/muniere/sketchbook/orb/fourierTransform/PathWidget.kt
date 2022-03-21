package net.muniere.sketchbook.orb.fourierTransform

import net.muniere.sketchbook.lib.graphics.vertex
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class PathWidget(graphics: PGraphics) : ModelWidget<PathModel>(graphics) {

  override fun doDraw(model: PathModel) {
    this.scope {
      it.noFill();
      it.stroke(model.color);

      this.shape(SApplet.ShapeMode.OPEN) {
        model.points.forEach { point ->
          it.vertex(point)
        }
      }
    }
  }
}
