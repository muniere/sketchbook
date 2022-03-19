package net.muniere.sketchbook.orb.reactionDiffusion

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal class GridWidget(graphics: PGraphics) : ModelWidget<GridModel>(graphics) {

  override fun doDraw(model: GridModel) {
    this.scope { graphics ->
      graphics.loadPixels()

      model.forEachIndexed { cell, row, column ->
        val index = column + row * model.width
        val value = 255 - ((cell.a - cell.b) * 255.0F).toInt()
        graphics.pixels[index] = 0xFF.shl(24) or value.shl(16) or value.shl(8) or value
      }

      graphics.updatePixels()
    }
  }
}
