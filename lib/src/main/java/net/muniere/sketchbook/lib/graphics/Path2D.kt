package net.muniere.sketchbook.lib.graphics

import kotlin.math.max
import kotlin.math.min

public final data class Path2D(
  public val points: List<Point2D>,
) {
  public operator fun plus(move: Move2D): Path2D {
    return Path2D(this.points.map { it + move })
  }

  public operator fun minus(move: Move2D): Path2D {
    return Path2D(this.points.map { it - move })
  }

  public fun width(): Float {
    return this.frame().width
  }

  public fun height(): Float {
    return this.frame().height
  }

  public fun bounds(): Rect2D {
    return this.frame().copy(origin = Point2D.zero())
  }

  public fun frame(): Rect2D {
    var minX = Float.MAX_VALUE
    var maxX = Float.MIN_VALUE
    var minY = Float.MAX_VALUE
    var maxY = Float.MIN_VALUE

    this.points.forEach {
      minX = min(it.x, minX)
      maxX = max(it.x, maxX)
      minY = min(it.y, minY)
      maxY = max(it.y, maxY)
    }

    return Rect2D(
      origin = Point2D(minX, minY),
      size = Size2D(maxX - minX, maxY - minY),
    )
  }
}
