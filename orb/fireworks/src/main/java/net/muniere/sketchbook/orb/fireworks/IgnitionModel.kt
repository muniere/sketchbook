package net.muniere.sketchbook.orb.fireworks

import net.muniere.sketchbook.lib.graphics.Rect2D

internal abstract class IgnitionModel {
  abstract fun performIn(rect: Rect2D): FireSeedModel
}

