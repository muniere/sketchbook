package net.muniere.sketchbook.orb.mengerSponge

import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PGraphics

internal final class SpongeWidget(graphics: PGraphics) : ModelWidget<SpongeModel>(graphics) {
  private val cube: CubeWidget = CubeWidget(graphics)

  override fun doDraw(model: SpongeModel) {
    this.scope {
      it.rotateX(model.rotation)
      it.rotateY(model.rotation * 0.5F)
      it.rotateZ(model.rotation * 0.2F)

      it.stroke(model.strokeColor)

      model.cubes.forEach { cube ->
        this.cube.model = cube
        this.cube.draw()
      }
    }
  }
}
