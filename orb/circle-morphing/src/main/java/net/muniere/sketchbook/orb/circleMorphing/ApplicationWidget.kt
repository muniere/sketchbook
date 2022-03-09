package net.muniere.sketchbook.orb.circleMorphing

import android.graphics.Color
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.Sketch
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  public var color: Color = Color.valueOf(Color.WHITE)

  override fun doDraw(model: ApplicationModel) {
    val path = model.path()

    this.scope {
      it.noFill()
      it.stroke(this.color)

      this.shape(Sketch.ShapeMode.CLOSED) { graphics ->
        path.points.forEach { point ->
          graphics.vertex(point.x, point.y)
        }
      }
    }
  }
}
