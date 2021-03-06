package net.muniere.sketchbook.orb.mengerSponge

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Move3D
import net.muniere.sketchbook.lib.graphics.Point3D
import net.muniere.sketchbook.lib.product

internal final class CubeModel(
  public val size: Float,
  public val center: Point3D,
) {
  public var color = Colors.parse("#FFFFFF")

  public fun spawn(): List<CubeModel> {
    return product(-1..1, -1..1, -1..1).mapNotNull { (x, y, z) ->
      if (listOf(x, y, z).count { it == 0 } >= 2) {
        return@mapNotNull null
      }

      val newSize = this.size / 3.0F
      val newCenter = this.center + Move3D(
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
