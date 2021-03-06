package net.muniere.sketchbook.lib.processing

import net.muniere.sketchbook.lib.graphics.Size2D
import processing.core.PApplet
import processing.core.PConstants
import processing.core.getLooping

public open class SApplet : PApplet() {

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

  protected val size: Size2D
    get() = Size2D(this.width.toFloat(), this.height.toFloat())

  private var plugins: List<SPlugin> = emptyList()

  protected open fun configure(): List<SPlugin> {
    return emptyList()
  }

  override fun settings() {
    this.size(this.width, this.height, this.renderer.rawValue)
  }

  final override fun setup() {
    this.plugins = this.configure()
    this.doSetup()
  }

  protected open fun doSetup() {
    // do nothing
  }

  final override fun draw() {
    this.pendingActions.forEach { it.run() }
    this.pendingActions.clear()

    this.plugins.forEach { it.onPreDraw(this) }
    this.push()
    this.doDraw()
    this.pop()
    this.plugins.forEach { it.onPostDraw(this) }
  }

  protected open fun doDraw() {
    // do nothing
  }

  public fun getLoopingDeeply(): Boolean {
    return this.g.getLooping()
  }

  public fun postNoLoop() {
    this.doOnNextDraw {
      this.noLoop()
    }
  }

  public fun doOnNextDraw(r: () -> Unit) {
    this.pendingActions.add(Runnable(r))
  }

  public fun doOnNextDraw(r: Runnable) {
    this.pendingActions.add(r)
  }

  private var pendingActions = mutableListOf<Runnable>()
}
