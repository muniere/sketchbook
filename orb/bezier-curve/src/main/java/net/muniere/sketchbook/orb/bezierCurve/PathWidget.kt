package net.muniere.sketchbook.orb.bezierCurve

import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.FloatRangeMapping
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.stroke
import processing.core.PConstants
import processing.core.PGraphics

internal class PathWidget(graphics: PGraphics) : ModelWidget<PathModel>(graphics) {

  internal sealed class Component {
    internal object Auxiliary : Component()

    internal class Curve(
      public val style: PathStyle,
    ) : Component()
  }

  public var color = Colors.WHITE
  public var components = listOf(
    Component.Auxiliary,
    Component.Curve(PathStyle.DISCRETE)
  )

  override fun doDraw(model: PathModel) {
    this.components.forEach { component ->
      when (component) {
        is Component.Auxiliary -> {
          this.doDrawAuxiliary(model)
        }
        is Component.Curve -> {
          this.doDrawCurve(model, component.style)
        }
      }
    }
  }

  private fun doDrawAuxiliary(model: PathModel) {
    this.scope { graphics ->
      graphics.colorMode(PConstants.HSB, 360.0F, 100.0F, 100.0F, 100.0F)
      graphics.noFill()

      val hueMap = FloatRangeMapping(
        domain = FloatRange(model.points.first().x, model.points.last().x),
        target = FloatRange(0.0F, 360.0F),
      )

      model.auxiliaries.forEach { line ->
        val p1 = line.start
        val p2 = line.stop
        val hue = hueMap.apply((p1.x + p2.x) / 2).toInt()
        val color = graphics.color(hue, 100, 100)
        graphics.stroke(color)
        graphics.line(p1.x, p1.y, p2.x, p2.y)
      }
    }
  }

  private fun doDrawCurve(model: PathModel, style: PathStyle) {
    this.scope {
      it.stroke(this.color)
      it.noFill()

      when (style) {
        PathStyle.DISCRETE -> model.points.forEach { point ->
          it.point(point.x, point.y)
        }
        PathStyle.CONTINUOUS -> this.shape(SApplet.ShapeMode.OPEN) { graphics ->
          model.points.forEach { point ->
            graphics.vertex(point.x, point.y)
          }
        }
      }
    }
  }
}
