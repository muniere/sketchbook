package net.muniere.sketchbook.orb.bezierCurve

import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.graphics.Line2D
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Point2DRange

internal final class CalculatorModel(
  public val start: Point2D,
  public val stop: Point2D,
  public var controls: List<Point2D>,
  public var resolution: Int,
) {

  init {
    require(this.resolution >= 0)
  }

  public fun run(): PathModel {
    val points = listOf<Point2D>() + this.start + this.controls + this.stop
    val auxiliaries = mutableListOf<Line2D>()

    val step = 1.0F / (this.resolution + 1)
    val range = FloatRange(step, 1.0F)

    val midpoints = range.sequence(step).map { amount ->
      this.reduce(
        points = points,
        amount = amount,
        onCompute = { auxiliaries.add(it) }
      )
    }

    return PathModel(
      points = listOf<Point2D>() + this.start.copy() + midpoints + this.stop.copy(),
      auxiliaries = auxiliaries,
    )
  }

  private fun reduce(
    points: List<Point2D>,
    amount: Float,
    onCompute: (line: Line2D) -> Unit,
  ): Point2D {
    var result = points

    while (result.size >= 2) {
      result = result.zipWithNext { start, stop ->
        onCompute.invoke(Line2D(start, stop))
        Point2DRange(start, stop).lerp(amount)
      }
    }

    return result.first()
  }
}
