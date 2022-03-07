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

  public enum class ShapeMode(
    public val rawValue: Int,
  ) {
    OPEN(PConstants.OPEN),
    CLOSED(PConstants.CLOSE),
  }

  protected open val renderer: Renderer
    get() = Renderer.P2D

  private var plugins: List<Plugin> = emptyList()

  final override fun settings() {
    this.size(this.size.width.toInt(), this.size.height.toInt(), this.renderer.rawValue)
  }

  protected open fun configure(): List<Plugin> {
    return emptyList()
  }

  final override fun setup() {
    this.plugins = this.configure()
    this.doSetup()
  }

  protected open fun doSetup() {
    // do nothing
  }

  final override fun draw() {
    this.plugins.forEach { it.onPreDraw(this) }
    this.doDraw()
    this.plugins.forEach { it.onPostDraw(this) }
  }

  protected open fun doDraw() {
    // do nothing
  }
}
