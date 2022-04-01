package net.muniere.sketchbook.orb.ulamSpiral

import android.graphics.Color
import net.muniere.sketchbook.lib.atlas.Line
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.graphics.line
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class LineWidget(graphics: PGraphics) : ModelWidget<Line>(graphics) {
  public var color: Color = Colors.WHITE
  public var boxSize: Size2D = Size2D.zero()

  override fun doDraw(model: Line) {
    val start = Point2D(
      x = model.start.column * this.boxSize.width + this.boxSize.width / 2,
      y = model.start.row * this.boxSize.height + this.boxSize.height / 2,
    )

    val stop = Point2D(
      x = model.stop.column * this.boxSize.width + this.boxSize.width / 2,
      y = model.stop.row * this.boxSize.height + this.boxSize.height / 2,
    )

    this.scope {
      it.stroke(this.color)
      it.line(start, stop)
    }
  }
}
