package net.muniere.sketchbook.orb.circleMorphing

import net.muniere.sketchbook.lib.graphics.rangeTo

internal interface MorphingModel {
  public val progress: Float
  public fun forward()
  public fun backward()
  public fun path(): PathModel
}

