package net.muniere.sketchbook.orb.bezierCurve

import net.muniere.sketchbook.lib.graphics.Line2D
import net.muniere.sketchbook.lib.graphics.Point2D

internal final class PathModel(
  public val points: List<Point2D>,
  public val auxiliaries: List<Line2D>,
)
