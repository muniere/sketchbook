package net.muniere.sketchbook.lib.graphics

import net.muniere.sketchbook.lib.FloatRange
import kotlin.random.Random

public final class Point3DRange(
  public val start: Point3D,
  public val end: Point3D,
) {

  public fun lerp(amount: Float): Point3D {
    return Point3D(
      x = FloatRange(this.start.x, this.end.x).lerp(amount),
      y = FloatRange(this.start.y, this.end.y).lerp(amount),
      z = FloatRange(this.start.z, this.end.z).lerp(amount),
    )
  }

  public fun random(): Point3D {
    return this.lerp(Random.nextFloat())
  }
}
