package net.muniere.sketchbook.orb.raycasting

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D

internal final class PointModel(
  public val position: Point2D,
) {
  public var color: Color = Colors.WHITE
}
