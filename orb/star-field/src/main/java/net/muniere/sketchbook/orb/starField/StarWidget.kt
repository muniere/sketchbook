package net.muniere.sketchbook.orb.starField

import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.map
import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class StarWidget(graphics: PGraphics) : ModelWidget<StarModel>(graphics) {

  override fun doDraw(model: StarModel) {
    val currentX = (model.center.x / model.center.z).map(
      domain = FloatRange(0.0F, 1.0F),
      target = FloatRange(0.0F, this.g.width.toFloat()),
    )

    val currentY = (model.center.y / model.center.z).map(
      domain = FloatRange(0.0F, 1.0F),
      target = FloatRange(0.0F, this.g.height.toFloat()),
    )

    val currentRadius = model.center.z.map(
      domain = FloatRange(this.g.width.toFloat(), 0.0F),
      target = FloatRange(0.0F, model.radius),
    )

    val originX = (model.center.x / model.origin.z).map(
      domain = FloatRange(0.0F, 1.0F),
      target = FloatRange(0.0F, this.g.width.toFloat()),
    )

    val originY = (model.center.y / model.origin.z).map(
      domain = FloatRange(0.0F, 1.0F),
      target = FloatRange(0.0F, this.g.height.toFloat()),
    )

    this.scope {
      it.fill(255)
      it.noStroke()
      it.circle(currentX, currentY, currentRadius)

      it.stroke(255, 64.0F)
      it.line(originX, originY, currentX, currentY)
    }
  }
}
