package net.muniere.sketchbook.orb.quadtree

import android.graphics.Color
import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class TreeWidget(graphics: PGraphics) : ModelWidget<TreeModel>(graphics) {

  public var fillColor: Color?
    get() = this.division.fillColor
    set(value) = this.division::fillColor.set(value)

  public var strokeColor: Color?
    get() = this.division.strokeColor
    set(value) = this.division::strokeColor.set(value)

  private val division = DivisionWidget(graphics)

  override fun doDraw(model: TreeModel) {
    model.walk(SearchStrategy.WIDTH_FIRST) {
      this.division.model = it
      this.division.draw()
    }
  }
}
