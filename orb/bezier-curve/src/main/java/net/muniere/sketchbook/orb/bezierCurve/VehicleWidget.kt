package net.muniere.sketchbook.orb.bezierCurve

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.circle
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import processing.core.PGraphics

internal class VehicleWidget(graphics: PGraphics) : ModelWidget<VehicleModel>(graphics) {
  public var color = Color.valueOf(Color.WHITE)

  override fun doDraw(model: VehicleModel) {
    this.scope {
      it.fill(this.color)
      it.noStroke()
      it.circle(model.center, model.radius * 2)
    }
  }
}
