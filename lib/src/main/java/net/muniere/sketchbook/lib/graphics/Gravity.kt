package net.muniere.sketchbook.lib.graphics

public final data class Gravity(
  public val x: Int,
  public val y: Int,
) {

  public companion object {
    public val TOP_LEFT = Gravity(x = -1, y = -1)
    public val TOP_CENTER = Gravity(x = 0, y = -1)
    public val TOP_RIGHT = Gravity(x = 1, y = -1)
    public val CENTER_LEFT = Gravity(x = -1, y = 0)
    public val CENTER = Gravity(x = 0, y = 0)
    public val CENTER_RIGHT = Gravity(x = 1, y = 0)
    public val BOTTOM_LEFT = Gravity(x = -1, y = 1)
    public val BOTTOM_CENTER = Gravity(x = 0, y = 1)
    public val BOTTOM_RIGHT = Gravity(x = 1, y = 1)
  }
}
