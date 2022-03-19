package net.muniere.sketchbook.orb.reactionDiffusion

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal class GridWidget(graphics: PGraphics) : ModelWidget<GridModel>(graphics) {
  public var scale: Int = 1

  override fun doDraw(model: GridModel) {
    val matrixWidth = model.width
    val scaledWidth = matrixWidth * this.scale

    this.scope { graphics ->
      graphics.loadPixels()

      model.forEachIndexed { cell, row, column ->
        val value = 255 - ((cell.a - cell.b) * 255.0F).toInt()
        val matrixIndex = matrixWidth * row + column
        val scaledIndex = scaledWidth * (row * this.scale) + (column * this.scale)

        repeat(this.scale) { rowOffset ->
          repeat(this.scale) { columnOffset ->
            val scaledOffset = (scaledWidth * rowOffset) + columnOffset
            graphics.pixels[scaledIndex + scaledOffset] = 0xFF.shl(24) or value.shl(16) or value.shl(8) or value
          }
        }
      }

      graphics.updatePixels()
    }
  }
}
