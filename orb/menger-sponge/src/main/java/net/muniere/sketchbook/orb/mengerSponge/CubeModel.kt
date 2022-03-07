package net.muniere.sketchbook.orb.mengerSponge

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.Point3D
import net.muniere.sketchbook.lib.product

public final class CubeModel(
  public val size: Float,
  public val center: Point3D,
) {
  public var color = Color.valueOf(0xFFFFFF)

  public fun spawn(): List<CubeModel> {
    return product(-1..1, -1..1, -1..1).mapNotNull { (x, y, z) ->
      if (listOf(x, y, z).count { it == 0 } >= 2) {
        return@mapNotNull null
      }

      val newSize = this.size / 3.0F
      val newCenter = this.center.plus(
        x = x * newSize,
        y = y * newSize,
        z = z * newSize,
      )

      return@mapNotNull CubeModel(
        size = newSize,
        center = newCenter,
      )
    }
  }
}
