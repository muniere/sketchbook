package net.muniere.sketchbook.orb.fireworks

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class FireworkWidget(graphics: PGraphics) : ModelWidget<FireworkModel>(graphics) {
  private val particle: ParticleWidget = ParticleWidget(graphics)

  override fun doDraw(model: FireworkModel) {
    if (!model.isActive) {
      return
    }

    model.particles.forEach {
      this.particle.model = it
      this.particle.alpha = model.remaining
      this.particle.draw()
    }
  }
}
