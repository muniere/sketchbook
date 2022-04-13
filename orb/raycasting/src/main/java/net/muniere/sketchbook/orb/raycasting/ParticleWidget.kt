package net.muniere.sketchbook.orb.raycasting

import net.muniere.sketchbook.lib.graphics.line
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class ParticleWidget(graphics: PGraphics) : ModelWidget<ParticleModel>(graphics) {

  override fun doDraw(model: ParticleModel) {
    this.scope {
      it.fill(model.color)
      it.stroke(model.rayColor)

      it.rect(model.left, model.top, model.width, model.height)

      model.points.forEach { point ->
        it.line(model.center, point.position)
      }
    }
  }
}
