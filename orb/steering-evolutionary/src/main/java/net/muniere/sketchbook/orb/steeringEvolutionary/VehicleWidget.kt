package net.muniere.sketchbook.orb.steeringEvolutionary

import net.muniere.sketchbook.lib.drawing.copy
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class VehicleWidget(graphics: PGraphics) : ModelWidget<VehicleModel>(graphics) {
  public var palette = VehiclePalette()

  override fun doDraw(model: VehicleModel) {
    val color = this.palette.body.copy(alpha = model.scoreFraction)

    this.scope {
      it.translate(model.center.x, model.center.y)
      it.rotate(model.velocity.toVector().heading() + Math.PI.toFloat() / 2)

      it.fill(color)
      it.noStroke()

      this.shape(SApplet.ShapeMode.CLOSED) { _ ->
        it.vertex(0.0F, -model.radius)
        it.vertex(-model.radius / 2, model.radius)
        it.vertex(model.radius / 2, model.radius)
      }

      it.stroke(this.palette.body)
      it.noFill()
      it.circle(0.0F, 0.0F, model.radius * 2)

      it.stroke(this.palette.reward)
      it.noFill()
      it.line(0.0F, 0.0F, 0.0F, -model.weight.rewardFactor * 100)
      it.circle(0.0F, 0.0F, model.sensor.rewardSight * 2)

      it.stroke(this.palette.penalty)
      it.noFill()
      it.line(0.0F, 0.0F, 0.0F, -model.weight.penaltyFactor * 100)
      it.circle(0.0F, 0.0F, model.sensor.penaltySight * 2)
    }
  }
}
