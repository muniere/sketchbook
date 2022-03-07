package net.muniere.sketchbook.lib.graphics

import processing.core.PConstants

public final data class Alignment(
  public val x: Int,
  public val y: Int,
) {

  public companion object {
    public val TOP_LEFT = Alignment(
      x = PConstants.LEFT,
      y = PConstants.TOP,
    )
    public val TOP_CENTER = Alignment(
      x = PConstants.CENTER,
      y = PConstants.TOP,
    )
    public val TOP_RIGHT = Alignment(
      x = PConstants.RIGHT,
      y = PConstants.TOP,
    )
    public val CENTER_LEFT = Alignment(
      x = PConstants.LEFT,
      y = PConstants.CENTER,
    )
    public val CENTER = Alignment(
      x = PConstants.CENTER,
      y = PConstants.CENTER,
    )
    public val CENTER_RIGHT = Alignment(
      x = PConstants.RIGHT,
      y = PConstants.CENTER,
    )
    public val BOTTOM_LEFT = Alignment(
      x = PConstants.LEFT,
      y = PConstants.BOTTOM,
    )
    public val BOTTOM_CENTER = Alignment(
      x = PConstants.CENTER,
      y = PConstants.BOTTOM,
    )
    public val BOTTOM_RIGHT = Alignment(
      x = PConstants.RIGHT,
      y = PConstants.BOTTOM,
    )
  }
}
