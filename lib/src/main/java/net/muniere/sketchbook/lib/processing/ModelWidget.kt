package net.muniere.sketchbook.lib.processing

import processing.core.PGraphics

public abstract class ModelWidget<Model>(graphics: PGraphics) : Widget(graphics) {
  public var model: Model? = null

  override fun draw() {
    this.model?.let(this::doDraw)
  }

  protected abstract fun doDraw(model: Model)
}
