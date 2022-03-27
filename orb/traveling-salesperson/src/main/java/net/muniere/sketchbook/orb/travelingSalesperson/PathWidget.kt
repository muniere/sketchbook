package net.muniere.sketchbook.orb.travelingSalesperson

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.circle
import net.muniere.sketchbook.lib.graphics.vertex
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class PathWidget(graphics: PGraphics) : ModelWidget<PathModel>(graphics) {
  public var color: Color = Colors.WHITE
  public var weight: Float = 1.0F

  override fun doDraw(model: PathModel) {
    // nodes
    this.scope { graphics ->
      graphics.stroke(this.color)
      graphics.fill(this.color)

      model.points.forEach {
        graphics.circle(it, 8.0F)
      }
    }

    // edges
    this.scope { graphics ->
      graphics.stroke(this.color)
      graphics.strokeWeight(this.weight)
      graphics.noFill()

      this.shape(SApplet.ShapeMode.OPEN) {
        model.points.forEach {
          graphics.vertex(it)
        }
      }
    }
  }
}
