package net.muniere.sketchbook.orb.pathFinding

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import processing.core.PGraphics

internal final class NodeWidget(graphics: PGraphics) : ModelWidget<NodeModel>(graphics) {
  public var color: Color = Colors.WHITE

  override fun doDraw(model: NodeModel) {
    this.scope {
      it.noStroke()
      it.fill(this.color)

      it.rect(
        model.spot.column * model.size.width,
        model.spot.row * model.size.height,
        model.size.width - 1,
        model.size.height - 1,
      )
    }
  }
}
