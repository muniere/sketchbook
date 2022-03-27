package net.muniere.sketchbook.orb.lifeGame

import android.graphics.Color
import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.graphics.rect
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  public var frame = Rect2D(
    origin = Point2D.zero(),
    size = Size2D.zero(),
  )

  public var aliveColor: Color = Colors.BLACK
  public var deadColor: Color = Colors.WHITE
  public var borderColor: Color = Colors.WHITE

  override fun doDraw(model: ApplicationModel) {
    val cellSize = Size2D(
      width = this.frame.width / model.grid.width,
      height = this.frame.height / model.grid.height,
    )

    this.scope {
      it.fill(this.deadColor)
      it.rect(this.frame)
    }

    this.scope {
      it.fill(this.aliveColor)
      it.noStroke()

      model.grid.walk { state, spot ->
        if (state == CellModel.DEAD) {
          return@walk
        }

        val origin = Point2D(
          x = this.frame.origin.x + spot.column * cellSize.width,
          y = this.frame.origin.y + spot.row * cellSize.height,
        )

        it.rect(origin, cellSize)
      }
    }

    this.scope {
      it.stroke(this.borderColor)
      it.noFill()

      val xs = FloatRange(cellSize.width, this.frame.width)
      val ys = FloatRange(cellSize.height, this.frame.height)

      xs.sequence(step = cellSize.width).forEach { x ->
        it.line(x, 0.0F, x, this.frame.height)
      }

      ys.sequence(step = cellSize.height).forEach { y ->
        it.line(0.0F, y, this.frame.width, y)
      }
    }
  }
}
