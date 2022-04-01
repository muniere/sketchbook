package net.muniere.sketchbook.orb.ulamSpiral

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.graphics.translate
import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal final class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  public var origin = Point2D.zero()
  public var components = setOf(SpiralComponent.PIECE)
  private val piece = PieceWidget(graphics)
  private val line = LineWidget(graphics)

  public fun setColor(color: Color) {
    this.piece.color = color
    this.line.color = color
  }

  public fun setBoxSize(size: Size2D) {
    this.piece.boxSize = size
    this.line.boxSize = size
  }

  public fun setStyle(style: PieceStyle) {
    this.piece.style = style
  }

  override fun doDraw(model: ApplicationModel) {
    this.scope {
      it.translate(this.origin)

      if (this.components.contains(SpiralComponent.PIECE)) {
        this.piece.model = model.piece
        this.piece.draw()
      }
      if (this.components.contains(SpiralComponent.LINE)) {
        this.line.model = model.line
        this.line.draw()
      }
    }
  }
}
