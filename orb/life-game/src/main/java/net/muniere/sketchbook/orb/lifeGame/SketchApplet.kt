package net.muniere.sketchbook.orb.lifeGame

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.math.Dimen
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.background
import kotlin.random.Random

internal final class SketchApplet : SApplet() {

  private object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#222222")
    }

    internal object World {
      internal const val SCALE = 16
      internal const val SPEED = 30
      internal const val SEED_RATE = 0.2F
      internal val ALIVE_COLOR = Colors.parse("#000000")
      internal val DEAD_COLOR = Colors.parse("#FFFFFF")
      internal val BORDER_COLOR = Colors.parse("#888888")
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun doSetup() {
    this.frameRate(Params.World.SPEED.toFloat())

    val dimen = Dimen(
      width = this.width / Params.World.SCALE,
      height = this.height / Params.World.SCALE,
    )

    val grid = GridModel.generate(dimen) {
      when (Random.nextFloat() < Params.World.SEED_RATE) {
        true -> CellModel.ALIVE
        false -> CellModel.DEAD
      }
    }

    this.model = ApplicationModel(grid)

    this.widget = ApplicationWidget(this.g).also {
      it.model = model
      it.frame.size = this.size
      it.aliveColor = Params.World.ALIVE_COLOR
      it.deadColor = Params.World.DEAD_COLOR
      it.borderColor = Params.World.BORDER_COLOR
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.Canvas.BACKGROUND_COLOR)

    // widget
    this.widget.draw()

    // update
    this.model.update()
  }
}
