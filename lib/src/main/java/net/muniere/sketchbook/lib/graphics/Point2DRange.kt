package net.muniere.sketchbook.lib.graphics

import net.muniere.sketchbook.lib.FloatRange
import kotlin.random.Random

public final class Point2DRange(
  public val start: Point2D,
  public val end: Point2D,
) {

  public fun lerp(amount: Float): Point2D {
    return Point2D(
      x = FloatRange(this.start.x, this.end.x).lerp(amount),
      y = FloatRange(this.start.y, this.end.y).lerp(amount),
    )
  }

  public fun random(): Point2D {
    return this.lerp(Random.nextFloat())
  }
}
