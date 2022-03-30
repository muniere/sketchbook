package net.muniere.sketchbook.orb.langtonAnt

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.fill
import processing.core.PGraphics
import processing.core.PVector

internal final class AntWidget(graphics: PGraphics) : ModelWidget<AntModel>(graphics) {
  public var frame = Rect2D(
    origin = Point2D.zero(),
    size = Size2D.zero(),
  )
  public var color: Color = Colors.parse("#000000")

  override fun doDraw(model: AntModel) {
    val center = this.frame.center

    val angle = when (model.direction) {
      Direction.NORTH -> 0.0F
      Direction.SOUTH -> Math.PI.toFloat()
      Direction.EAST -> Math.PI.toFloat() / 2
      Direction.WEST -> -Math.PI.toFloat() / 2
    }

    val vectors = listOf(
      PVector(0.0F, -this.frame.height / 2),
      PVector(-this.frame.width / 4, this.frame.height / 2),
      PVector(+this.frame.width / 4, this.frame.height / 2),
    ).map {
      it.rotate(angle)
    }

    val points = vectors.map {
      center.movingBy(it.x, it.y)
    }

    this.scope { graphics ->
      graphics.noStroke()
      graphics.fill(this.color)

      this.shape(SApplet.ShapeMode.CLOSED) {
        points.forEach {
          graphics.vertex(it.x, it.y)
        }
      }
    }
  }
}

