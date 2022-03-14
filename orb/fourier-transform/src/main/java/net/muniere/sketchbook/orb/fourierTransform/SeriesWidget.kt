package net.muniere.sketchbook.orb.fourierTransform

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class SeriesWidget(graphics: PGraphics) : ModelWidget<SeriesModel>(graphics) {
  public var trackWeight: Float = 0.0F
  public var handWeight: Float = 0.0F
  public var pointRadius: Float = 0.0F

  private val circle = CircleWidget(graphics)

  override fun doDraw(model: SeriesModel) {
    model.circles.forEach { circle ->
      this.circle.model = circle
      this.circle.trackWeight = this.trackWeight
      this.circle.handWeight = this.handWeight
      this.circle.pointRadius = this.pointRadius
      this.circle.draw()
    }
  }
}
