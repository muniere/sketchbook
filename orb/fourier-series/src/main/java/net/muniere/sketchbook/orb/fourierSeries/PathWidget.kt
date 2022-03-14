package net.muniere.sketchbook.orb.fourierSeries

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.SketchApplet
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class PathWidget(graphics: PGraphics) : ModelWidget<PathModel>(graphics) {
  public var origin = Point2D.zero()
  public var scaleX: Float = 1.0F
  public var scaleY: Float = 1.0F

  public fun first(): Point2D? {
    val model = this.model ?: run {
      return null
    }

    return this.origin.copy(
      y = model.first() * this.scaleY,
    )
  }

  public fun last(): Point2D? {
    val model = this.model ?: run {
      return null
    }

    return this.origin.copy(
      y = model.last() * this.scaleY,
    )
  }

  override fun doDraw(model: PathModel) {
    this.scope {
      it.noFill()
      it.stroke(model.color)

      this.shape(SketchApplet.ShapeMode.OPEN) {
        model.values.forEachIndexed { i, value ->
          val x = this.origin.x + i * this.scaleX
          val y = this.origin.y + value * this.scaleY
          it.vertex(x, y)
        }
      }
    }
  }
}
