package net.muniere.sketchbook.orb.steeringEvolutionary

import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import processing.core.PGraphics

internal final class MaterialWidget(graphics: PGraphics) : ModelWidget<MaterialModel>(graphics) {
  public var palette = MaterialPalette()

  override fun doDraw(model: MaterialModel) {
    val color = when (model.kind) {
      MaterialKind.MEDICINE -> this.palette.medicine
      MaterialKind.POISON -> this.palette.poison
      MaterialKind.NOTHING -> this.palette.nothing
    }

    this.scope {
      it.fill(color)
      it.noStroke()

      it.translate(model.center.x, model.center.y)
      it.circle(0.0F, 0.0F, model.radius * 2)
    }
  }
}
