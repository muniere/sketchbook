package net.muniere.sketchbook.orb.steeringEvolutionary

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors

internal final data class MaterialPalette(
  public val medicine: Color = Colors.parse("#4F9301"),
  public val poison: Color = Colors.parse("#C50046"),
  public val nothing: Color = Colors.parse("#FFFFFF"),
)
