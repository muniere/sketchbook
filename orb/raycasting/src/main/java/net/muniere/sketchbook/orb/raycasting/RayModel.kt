package net.muniere.sketchbook.orb.raycasting

import net.muniere.sketchbook.lib.graphics.Point2D
import processing.core.PVector

internal final class RayModel(
  public var direction: PVector,
  public var position: Point2D,
) {

  public fun look(point: Point2D) {
    this.direction.set(
      point.x - this.position.x,
      point.y - this.position.y,
    )
    this.direction.normalize()
  }

  public fun cast(wall: WallModel): Point2D? {
    val (x1, y1) = wall.p1
    val (x2, y2) = wall.p2
    val (x3, y3) = this.position
    val x4 = x3 + this.direction.x
    val y4 = y3 + this.direction.y

    val d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4)
    if (d == 0.0F) {
      return null
    }
    val t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / d
    val u = ((x1 - x3) * (y1 - y2) - (y1 - y3) * (x1 - x2)) / d

    if (t < 0 || 1 < t) {
      return null
    }
    if (u < 0) {
      return null
    }

    return Point2D(
      x = x1 + t * (x2 - x1),
      y = y1 + t * (y2 - y1),
    )
  }
}
