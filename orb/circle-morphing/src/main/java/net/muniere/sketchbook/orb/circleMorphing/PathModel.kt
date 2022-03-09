package net.muniere.sketchbook.orb.circleMorphing

import net.muniere.sketchbook.lib.graphics.Point2D

internal final class PathModel(
  public val points: List<Point2D>,
) {
  public val length: Int
    get() = this.points.size
}
