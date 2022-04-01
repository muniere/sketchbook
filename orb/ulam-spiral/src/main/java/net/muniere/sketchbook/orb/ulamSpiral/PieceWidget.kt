package net.muniere.sketchbook.orb.ulamSpiral

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import processing.core.PConstants
import processing.core.PGraphics

internal final class PieceWidget(graphics: PGraphics) : ModelWidget<PieceModel>(graphics) {
  public var color: Color = Colors.WHITE
  public var boxSize: Size2D = Size2D.zero()
  public var style: PieceStyle = PieceStyle.Text(textSize = 32.0F)

  override fun doDraw(model: PieceModel) {
    when (val style = this.style) {
      is PieceStyle.Text -> {
        val x = model.spot.column * this.boxSize.width + this.boxSize.width / 2
        val y = model.spot.row * this.boxSize.height + this.boxSize.height / 2
        val label = (model.stepCount + 1).toString()

        this.scope {
          it.fill(this.color)
          it.textSize(style.textSize)
          it.textAlign(PConstants.CENTER, PConstants.CENTER)
          it.text(label, x, y)
        }
      }
      is PieceStyle.Circle -> {
        if (!style.test(model.stepCount + 1)) {
          return
        }

        val x = model.spot.column * this.boxSize.width + this.boxSize.width / 2
        val y = model.spot.row * this.boxSize.height + this.boxSize.height / 2

        this.scope {
          it.fill(this.color)
          it.circle(x, y, style.radius * 2)
        }
      }
    }
  }
}
