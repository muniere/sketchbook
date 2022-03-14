package net.muniere.sketchbook.orb.fourierSeries

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class SeriesWidget(graphics: PGraphics) : ModelWidget<SeriesModel>(graphics) {
  public var origin = Point2D.zero()
  public var trackWeight: Float = 1.0F
  public var handWeight: Float = 1.0F
  public var pointRadius: Float = 1.0F

  private val circle = CircleWidget(graphics)

  public fun epicycleCenter(): Point2D? {
    return this.model?.last()?.epicycleCenter()
  }

  override fun doDraw(model: SeriesModel) {
    this.scope {
      it.translate(this.origin.x, this.origin.y)

      model.circles.forEachIndexed { i, circle ->
        this.circle.model = circle
        this.circle.trackWeight = when (i) {
          0 -> this.trackWeight
          else -> 0.0F
        }
        this.circle.handWeight = this.handWeight
        this.circle.pointRadius = this.pointRadius
        this.circle.draw()
      }
    }
  }
}
