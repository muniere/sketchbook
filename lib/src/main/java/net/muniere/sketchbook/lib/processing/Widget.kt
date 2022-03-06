package net.muniere.sketchbook.lib.processing

import processing.core.PGraphics

public abstract class Widget(
  protected val g: PGraphics
) {

  public abstract fun draw()

  protected fun <T> scope(action: (PGraphics) -> T): T {
    this.g.push()
    val result = action.invoke(this.g)
    this.g.pop()
    return result
  }

  protected fun shape(mode: Sketch.ShapeMode = Sketch.ShapeMode.CLOSED, action: (PGraphics) -> Unit) {
    this.g.beginShape()
    action.invoke(this.g)
    this.g.endShape(mode.rawValue)
  }
}
