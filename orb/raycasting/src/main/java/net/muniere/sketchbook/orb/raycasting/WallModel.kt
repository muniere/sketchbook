package net.muniere.sketchbook.orb.raycasting

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D

internal final class WallModel(
  public val p1: Point2D,
  public val p2: Point2D,
) {
  public var color: Color = Colors.WHITE
}
