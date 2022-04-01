package net.muniere.sketchbook.orb.ulamSpiral

import net.muniere.sketchbook.lib.atlas.Direction
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.math.Spot
import net.muniere.sketchbook.lib.math.isPrime
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.background

internal final class SketchApplet : SApplet() {

  private object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#222222")
    }

    internal object Spiral {
      internal const val DIAMETER = 399
      internal const val SPEED = 500
      internal val COMPONENTS = setOf(SpiralComponent.PIECE)
    }

    internal object Piece {
      internal val FILL_COLOR = Colors.parse("#FFFFFF")
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun doSetup() {
    val piece = PieceModel(
      spot = Spot(
        row = Params.Spiral.DIAMETER / 2,
        column = Params.Spiral.DIAMETER / 2,
      ),
      direction = Direction.EAST
    )

    val style = PieceStyle.Circle(
      radius = 2.0F,
      predicate = ::isPrime
    )

    val spiralSize = Size2D.square(
      maxOf(this.width, this.height).toFloat()
    )

    val spiralOrigin = Point2D(
      x = (this.width - spiralSize.width) / 2.0F,
      y = (this.height - spiralSize.height) / 2.0F,
    )

    val boxSize = spiralSize / Params.Spiral.DIAMETER.toFloat()

    this.model = ApplicationModel(piece)

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
      it.origin = spiralOrigin
      it.components = Params.Spiral.COMPONENTS
      it.setStyle(style)
      it.setBoxSize(boxSize)
      it.setColor(Params.Piece.FILL_COLOR)
    }

    this.g.background(Params.Canvas.BACKGROUND_COLOR)
  }

  override fun doDraw() {
    repeat(Params.Spiral.SPEED) {
      if (this.model.stepCount > Params.Spiral.DIAMETER * Params.Spiral.DIAMETER) {
        return@repeat
      }
      this.widget.draw()
      this.model.update()
    }

    if (this.model.stepCount > Params.Spiral.DIAMETER * Params.Spiral.DIAMETER) {
      this.noLoop()
    }
  }
}
