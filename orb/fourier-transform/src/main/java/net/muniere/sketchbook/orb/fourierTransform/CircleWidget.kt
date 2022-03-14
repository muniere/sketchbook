package net.muniere.sketchbook.orb.fourierTransform

import net.muniere.sketchbook.lib.graphics.circle
import net.muniere.sketchbook.lib.graphics.line
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class CircleWidget(graphics: PGraphics) : ModelWidget<CircleModel>(graphics) {

  public var trackWeight: Float = 0.0F
  public var handWeight: Float = 0.0F
  public var pointRadius: Float = 0.0F

  override fun doDraw(model: CircleModel) {
    val pointCenter = model.epicycleCenter()

    this.scope {
      if (this.trackWeight > 0) {
        it.stroke(model.color)
        it.strokeWeight(this.trackWeight)
        it.noFill()

        it.circle(model.center, model.radius * 2)
      }

      if (this.handWeight > 0) {
        it.stroke(model.color)
        it.strokeWeight(this.handWeight)
        it.noFill()
        it.line(model.center, pointCenter)
      }

      if (this.pointRadius > 0) {
        it.noStroke()
        it.fill(model.color)
        it.circle(pointCenter, this.pointRadius)
      }
    }
  }
}
