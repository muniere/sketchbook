package net.muniere.sketchbook.lib.processing

import processing.core.PApplet

public final data class MetricsEntity(
  public val frameCount: Int,
  public val frameRate: Float,
  public val keyCode: Int,
  public val keyName: String,
  public val mouseX: Int,
  public val mouseY: Int,
) {

  public companion object {
    public fun parse(applet: PApplet): MetricsEntity {
      val keyName = when (applet.key) {
        ' ' -> "SPACE"
        else -> applet.key.toString()
      }

      return MetricsEntity(
        frameCount = applet.frameCount,
        frameRate = applet.frameRate,
        keyCode = applet.keyCode,
        keyName = keyName,
        mouseX = applet.mouseX,
        mouseY = applet.mouseY,
      )
    }
  }
}
