package net.muniere.sketchbook.orb.langtonAnt

import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class GridWidget(graphics: PGraphics) : ModelWidget<GridModel>(graphics) {

  public var frame = Rect2D(
    origin = Point2D.zero(),
    size = Size2D.zero(),
  )
  public var fillColor = Colors.parse("#000000")
  public var lineColor = Colors.parse("#888888")

  override fun doDraw(model: GridModel) {
    val unit = Size2D(
      width = this.frame.width / model.dimen.width,
      height = this.frame.height / model.dimen.height,
    )

    // cell
    this.scope { graphics ->
      graphics.noStroke()
      graphics.fill(this.fillColor)

      model.forEachIndexed { cell, spot ->
        if (cell == CellModel.WHITE) {
          return@forEachIndexed
        }

        val w = unit.width
        val h = unit.height
        val x = spot.column * w
        val y = spot.row * h
        graphics.rect(x, y, w, h)
      }
    }

    // border
    this.scope { graphics ->
      graphics.noFill()
      graphics.stroke(this.lineColor)
      graphics.strokeWeight(0.3F)

      val xs = FloatRange(0.0F, this.frame.width)
      val ys = FloatRange(0.0F, this.frame.height)

      xs.sequence(step = unit.width).forEach { x ->
        graphics.line(x, 0.0F, x, this.frame.height)
      }
      ys.sequence(step = unit.width).forEach { y ->
        graphics.line(0.0F, y, this.frame.width, y)
      }
    }
  }
}
