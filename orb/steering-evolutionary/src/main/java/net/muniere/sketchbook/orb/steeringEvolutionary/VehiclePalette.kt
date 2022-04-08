package net.muniere.sketchbook.orb.steeringEvolutionary

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors

internal final data class VehiclePalette(
  public val body: Color = Colors.parse("#CEBA00"),
  public val sensor: Color = Colors.parse("#f5e77a"),
  public val reward: Color = Colors.parse("#4F9301"),
  public val penalty: Color = Colors.parse("#C50046"),
)
