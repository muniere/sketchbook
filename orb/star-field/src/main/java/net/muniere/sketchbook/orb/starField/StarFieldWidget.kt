package net.muniere.sketchbook.orb.starField

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class StarFieldWidget(graphics: PGraphics) : ModelWidget<StarFieldModel>(graphics) {

  private val star: StarWidget = StarWidget(graphics)

  override fun doDraw(model: StarFieldModel) {
    this.scope {
      it.translate(
        it.width / 2.0F,
        it.height / 2.0F,
      )

      model.stars.forEach { star ->
        this.star.model = star
        this.star.draw()
      }
    }
  }
}
