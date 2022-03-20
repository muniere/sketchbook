package net.muniere.sketchbook.orb.pathFinding

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors

internal data class GraphPalette(
  public var wallColor: Color = Colors.WHITE,
  public var baseColor: Color = Colors.WHITE,
  public var openColor: Color = Colors.WHITE,
  public var closedColor: Color = Colors.WHITE,
  public var runningColor: Color = Colors.WHITE,
  public var answerColor: Color = Colors.WHITE,
  public var abortColor: Color = Colors.WHITE,
)
