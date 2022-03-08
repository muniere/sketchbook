package net.muniere.sketchbook.orb.fireworks

import net.muniere.sketchbook.lib.physics.CircularMaterial
import net.muniere.sketchbook.lib.physics.Material
import net.muniere.sketchbook.lib.physics.RectangularMaterial
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class ParticleWidget(graphics: PGraphics) : ModelWidget<Material>(graphics) {
  public var alpha: Float = -1.0F

  override fun doDraw(model: Material) {
    val alpha = this.alpha.takeIf { it >= 0 }?.coerceIn(0.0F, 1.0F)

    this.scope {
      when (val fillColor = model.fillColor) {
        null -> it.noFill()
        else -> when (alpha) {
          null -> it.fill(fillColor)
          else -> it.fill(fillColor, alpha)
        }
      }
      when (val strokeColor = model.strokeColor) {
        null -> it.noStroke()
        else -> when (alpha) {
          null -> it.stroke(strokeColor)
          else -> it.stroke(strokeColor, alpha)
        }
      }
      when (model) {
        is CircularMaterial -> it.circle(model.center.x, model.center.y, model.radius * 2.0F)
        is RectangularMaterial -> it.rect(model.left, model.top, model.width, model.height)
      }
    }
  }
}
