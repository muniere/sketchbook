package net.muniere.sketchbook.orb.mengerSponge

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.processing.SketchApplet
import net.muniere.sketchbook.lib.processing.background

internal final class SketchApplet : SketchApplet() {

  private object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#222222")
    }

    internal object Sponge {
      internal val FILL_COLOR = Colors.parse("#FFFFFF")
      internal val STROKE_COLOR = Colors.parse("#666666")
      internal const val SIZE = 200.0F
    }

    internal object Perspective {
      internal const val ROTATION_SPEED = 0.01F
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override val renderer: Renderer
    get() = Renderer.P3D

  override fun doSetup() {
    val sponge = SpongeModel(
      size = Params.Sponge.SIZE,
    ).also {
      it.fillColor = Params.Sponge.FILL_COLOR
      it.strokeColor = Params.Sponge.STROKE_COLOR
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
    this.g.background(Params.Canvas.BACKGROUND_COLOR)
    this.g.translate(this.width / 2.0F, this.height / 2.0F)
    this.g.lights()

    // widget
    this.widget.draw()

    // update
    this.model.rotate(Params.Perspective.ROTATION_SPEED)
  }

  override fun touchEnded() {
    this.model.update()
  }
}
