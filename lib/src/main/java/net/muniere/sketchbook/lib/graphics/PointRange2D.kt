package net.muniere.sketchbook.lib.graphics

import net.muniere.sketchbook.lib.lerp
import kotlin.random.Random

public final class PointRange2D(
  public val start: Point2D,
  public val end: Point2D,
) {

  public fun lerp(amount: Float): Point2D {
    return Point2D(
      x = (this.start.x..this.end.x).lerp(amount),
      y = (this.start.y..this.end.y).lerp(amount),
    )
  }

  public fun random(): Point2D {
    return this.lerp(Random.nextFloat())
  }
}
