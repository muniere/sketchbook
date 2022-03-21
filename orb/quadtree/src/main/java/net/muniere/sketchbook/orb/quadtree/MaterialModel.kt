package net.muniere.sketchbook.orb.quadtree

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.physics.CircularMaterial

internal final class MaterialModel(
  radius: Float,
  center: Point2D,
) : CircularMaterial(
  radius = radius,
  center = center,
) {
  public var tag = MaterialTag.NORMAL

  public val zone: Rect2D
    get() = Rect2D(
      origin = Point2D(
        x = this.left - this.radius,
        y = this.top - this.radius,
      ),
      size = Size2D.square(this.radius * 4)
    )
}
