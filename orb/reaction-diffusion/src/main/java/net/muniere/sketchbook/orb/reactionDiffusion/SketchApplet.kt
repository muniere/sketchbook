package net.muniere.sketchbook.orb.reactionDiffusion

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.math.Dimen
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.SketchApplet
import net.muniere.sketchbook.lib.processing.background
import processing.core.PConstants

internal final class SketchApplet : SketchApplet() {

  private object Params {
    internal object Canvas {
      internal val COLOR = Colors.parse("#333333")
      internal const val SCALE = 4
    }

    internal object Diffusion {
      internal const val SIZE = 240
      internal const val SPEED = 1
      internal const val A = 1.0F
      internal const val B = 0.5F
      internal const val FEED = 0.055F
      internal const val KILL = 0.062F
    }

    internal object Seed {
      internal const val RADIUS = 4.0f
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g)
  )

  override fun settings() {
    this.size(
      Params.Diffusion.SIZE * Params.Canvas.SCALE,
      Params.Diffusion.SIZE * Params.Canvas.SCALE,
      PConstants.P2D,
    )
  }

  override fun doSetup() {
    super.doSetup()

    this.pixelDensity = 1

    val dimen = Dimen(
      width = Params.Diffusion.SIZE,
      height = Params.Diffusion.SIZE,
    )

    val seed = Rect2D(
      origin = Point2D(
        x = dimen.width / 2 - Params.Seed.RADIUS,
        y = dimen.height / 2 - Params.Seed.RADIUS,
      ),
      size = Size2D.square(Params.Seed.RADIUS * 2),
    )

    val factory = GridFactories.Default(seed)

    val diffusion = DiffusionModel(
      a = Params.Diffusion.A,
      b = Params.Diffusion.B,
      feed = Params.Diffusion.FEED,
      kill = Params.Diffusion.KILL,
    )

    this.model = ApplicationModel(
      grid = GridModel.generate(dimen, factory),
      diffusion = diffusion,
    )

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
      it.scale = Params.Canvas.SCALE
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.Canvas.COLOR)

    // widget
    this.widget.draw()

    // update
    this.model.update(speed = Params.Diffusion.SPEED)
  }
}
