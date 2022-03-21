package net.muniere.sketchbook.orb.quadtree

import android.graphics.Color
import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {

  public var fillColor: Color?
    get() = this.tree.fillColor
    set(value) = this.tree::fillColor.set(value)

  public var strokeColor: Color?
    get() = this.tree.strokeColor
    set(value) = this.tree::strokeColor.set(value)

  private val tree = TreeWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    this.tree.model = model.tree
    this.tree.draw()
  }
}
