package net.muniere.sketchbook.lib.processing

import processing.core.PApplet

public final class FrameClock(
  private val applet: PApplet,
  private val scale: Float,
) {
  public val time: Float
    get() = this.applet.frameCount * this.scale
}
