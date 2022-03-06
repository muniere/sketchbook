package net.muniere.sketchbook.lib.processing

import net.muniere.sketchbook.lib.graphics.Size2D
import processing.core.PApplet
import processing.core.PConstants

public open class Sketch(
  protected val size: Size2D,
) : PApplet() {

  public enum class Renderer(
    public val rawValue: String,
  ) {
    P2D(PConstants.P2D),
    P3D(PConstants.P3D),
  }

  protected open val renderer: Renderer
    get() = Renderer.P2D

  override fun settings() {
    this.size(this.size.width.toInt(), this.size.height.toInt(), this.renderer.rawValue)
  }
}
