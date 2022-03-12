package net.muniere.sketchbook.orb.mengerSponge

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.Sketch
import net.muniere.sketchbook.lib.processing.background

public final class Sketch(size: Size2D) : Sketch(size) {

  private object Params {
    internal val CANVAS_COLOR = Colors.parse("#222222")
    internal val FILL_COLOR = Colors.parse("#FFFFFF")
    internal val STROKE_COLOR = Colors.parse("#666666")

    internal const val SPONGE_SIZE = 200.0F
    internal const val ROTATION_SPEED = 0.01F
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override val renderer: Renderer
    get() = Renderer.P3D

  override fun doSetup() {
    val sponge = SpongeModel(
      size = Params.SPONGE_SIZE,
    ).also {
      it.fillColor = Params.FILL_COLOR
      it.strokeColor = Params.STROKE_COLOR
    }

    this.model = ApplicationModel(
      sponge = sponge,
    )

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.CANVAS_COLOR)
    this.g.translate(this.width / 2.0F, this.height / 2.0F)
    this.g.lights()

    // widget
    this.widget.draw()

    // update
    this.model.rotate(Params.ROTATION_SPEED)
  }

  override fun touchEnded() {
    this.model.update()
  }
}
