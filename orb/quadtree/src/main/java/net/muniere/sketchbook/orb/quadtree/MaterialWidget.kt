package net.muniere.sketchbook.orb.quadtree

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.circle
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class MaterialWidget(graphics: PGraphics) : ModelWidget<MaterialModel>(graphics) {

  override fun doDraw(model: MaterialModel) {
    this.scope {
      when (model.tag) {
        MaterialTag.NORMAL -> {
          it.fill(model.fillColor)
          it.stroke(model.strokeColor)
        }
        MaterialTag.FOCUSED -> {
          it.fill(Colors.parse("#ff1111"))
          it.noStroke()
        }
      }

      it.circle(model.center, model.radius * 2)
    }
  }
}
