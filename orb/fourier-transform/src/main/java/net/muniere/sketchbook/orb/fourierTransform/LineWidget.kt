package net.muniere.sketchbook.orb.fourierTransform

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Line2D
import net.muniere.sketchbook.lib.graphics.line
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class LineWidget(graphics: PGraphics) : ModelWidget<Line2D>(graphics) {
  public var color: Color = Colors.WHITE
  public var weight: Float = 1.0F

  override fun doDraw(model: Line2D) {
    this.scope {
      it.stroke(this.color)
      it.strokeWeight(this.weight)
      it.line(model.start, model.stop)
    }
  }
}
