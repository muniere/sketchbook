package net.muniere.sketchbook.orb.starField

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class StarFieldWidget(graphics: PGraphics) : ModelWidget<StarFieldModel>(graphics) {

  private val star: StarWidget = StarWidget(graphics)

  override fun doDraw(model: StarFieldModel) {
    this.g.translate(
      this.g.width / 2.0F,
      this.g.height / 2.0F,
    )

    model.stars.forEach {
      this.star.model = it
      this.star.draw()
    }
  }
}
