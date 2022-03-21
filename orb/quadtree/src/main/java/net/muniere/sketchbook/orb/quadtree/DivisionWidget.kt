package net.muniere.sketchbook.orb.quadtree

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.rect
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class DivisionWidget(graphics: PGraphics) : ModelWidget<DivisionModel>(graphics) {
  public var fillColor: Color? = null
  public var strokeColor: Color? = null

  private var material = MaterialWidget(graphics)

  override fun doDraw(model: DivisionModel) {
    if (this.strokeColor != null || this.fillColor != null) {
      val boundary = model.boundary

      this.scope {
        it.stroke(this.strokeColor)
        it.fill(this.fillColor)
        it.rect(boundary)
      }
    }

    model.materials.forEach {
      this.material.model = it
      this.material.draw()
    }
  }
}
