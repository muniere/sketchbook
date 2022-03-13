package net.muniere.sketchbook.lib.drawing

import android.graphics.Color

public final data class ColorDiff(
  public val red: Float,
  public val green: Float,
  public val blue: Float,
  public val alpha: Float,
) {
  public companion object {
    public fun measure(a: Color, b: Color): ColorDiff {
      return ColorDiff(
        red = a.red() - b.red(),
        green = a.green() - b.green(),
        blue = a.blue() - b.blue(),
        alpha = a.alpha() - b.alpha(),
      )
    }
  }

  public operator fun plus(other: ColorDiff) = ColorDiff(
    this.red + other.red,
    this.green + other.green,
    this.blue + other.blue,
    this.alpha + other.alpha,
  )

  public operator fun minus(other: ColorDiff) = ColorDiff(
    this.red - other.red,
    this.green - other.green,
    this.blue - other.blue,
    this.alpha - other.alpha,
  )

  public operator fun times(value: Float) = ColorDiff(
    this.red * value,
    this.green * value,
    this.blue * value,
    this.alpha * value,
  )
}
