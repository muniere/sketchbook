package net.muniere.sketchbook.lib.processing

import android.util.Size
import processing.core.PApplet
import processing.core.PConstants

public open class Sketch(
  protected val size: Size,
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
    this.size(this.size.width, this.size.height, this.renderer.rawValue)
  }
}
