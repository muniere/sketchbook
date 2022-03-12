package net.muniere.sketchbook.orb.fireworks

import net.muniere.sketchbook.lib.drawing.copy
import net.muniere.sketchbook.lib.physics.CircularMaterial
import net.muniere.sketchbook.lib.physics.Material
import net.muniere.sketchbook.lib.physics.RectangularMaterial
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class ParticleWidget(graphics: PGraphics) : ModelWidget<Material>(graphics) {
  public var alpha: Float = 1.0F

  override fun doDraw(model: Material) {
    val alpha = this.alpha.coerceIn(0.0F, 1.0F)

    this.scope {
      it.fill(model.fillColor?.copy(alpha = alpha))
      it.stroke(model.strokeColor?.copy(alpha = alpha))

      when (model) {
        is CircularMaterial -> it.circle(model.center.x, model.center.y, model.radius * 2.0F)
        is RectangularMaterial -> it.rect(model.left, model.top, model.width, model.height)
      }
    }
  }
}
