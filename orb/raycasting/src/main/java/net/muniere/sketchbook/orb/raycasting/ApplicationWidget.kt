package net.muniere.sketchbook.orb.raycasting

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {

  private val wall = WallWidget(graphics)
  private val particle = ParticleWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    model.walls.forEach {
      this.wall.model = it
      this.wall.draw()
    }

    this.particle.model = model.particle
    this.particle.draw()
  }
}
