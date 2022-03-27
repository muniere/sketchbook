package net.muniere.sketchbook.orb.circleMorphing

import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.rangeTo
import kotlin.math.PI

internal final object PathModels {

  public fun circle(radius: Float, resolution: Int): PathModel {
    val step = (2 * PI).toFloat() / resolution
    val range = FloatRange(0.0F, 2 * PI.toFloat())

    val points = range.sequence(step).map { angle ->
      Point2D.polar(radius, angle)
    }

    return PathModel(points.toList())
  }

  public fun polygon(n: Int, radius: Float, resolution: Int): PathModel {
    val division = (2 * PI).toFloat() / n
    val step = (2 * PI).toFloat() / resolution

    val points = (0 until n).flatMap { i ->
      val startAngle = i * division
      val startPoint = Point2D.polar(radius, startAngle)

      val endAngle = (i + 1) * division
      val endPoint = Point2D.polar(radius, endAngle)

      val angleRange = FloatRange(startAngle, endAngle)
      val pointRange = startPoint..endPoint

      return@flatMap angleRange.sequence(step).map { angle ->
        val amount = (angle % division) / (endAngle - startAngle)
        return@map pointRange.lerp(amount)
      }
    }

    return PathModel(points)
  }
}
