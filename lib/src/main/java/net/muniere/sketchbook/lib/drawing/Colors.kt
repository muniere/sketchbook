package net.muniere.sketchbook.lib.drawing

import android.graphics.Color
import kotlin.math.floor
import kotlin.math.roundToInt
import kotlin.random.Random

public final object Colors {

  public val WHITE: Color
    get() = Color.valueOf(Color.WHITE)

  public val BLACK: Color
    get() = Color.valueOf(Color.BLACK)

  public fun parse(color: String): Color {
    return Color.valueOf(Color.parseColor(color))
  }

  public fun random(): Color {
    return this.random(Random.nextFloat())
  }

  public fun random(alpha: Float): Color {
    return Color.valueOf(
      Random.nextFloat(),
      Random.nextFloat(),
      Random.nextFloat(),
      alpha,
    )
  }
}

public operator fun Color.plus(diff: ColorDiff) = Color.valueOf(
  this.red() + diff.red,
  this.green() + diff.green,
  this.blue() + diff.blue,
  this.alpha() + diff.alpha,
)

public operator fun Color.minus(diff: ColorDiff) = Color.valueOf(
  this.red() - diff.red,
  this.green() - diff.green,
  this.blue() - diff.blue,
  this.alpha() - diff.alpha,
)

public fun Color.toRGB(): Int {
  val r = (this.red() * 255.0F).roundToInt()
  val g = (this.green() * 255.0F).roundToInt()
  val b = (this.blue() * 255.0f).roundToInt()
  return (r shl 16) or (g shl 8) or b
}

public fun Color.quantize(factor: Int): Color {
  return Color.valueOf(
    floor(this.red() * factor) / (factor - 1),
    floor(this.green() * factor) / (factor - 1),
    floor(this.blue() * factor) / (factor - 1),
    this.alpha(),
  )
}

public fun Color.copy(
  red: Float? = null,
  green: Float? = null,
  blue: Float? = null,
  alpha: Float? = null,
) = Color.valueOf(
  red ?: this.red(),
  green ?: this.green(),
  blue ?: this.blue(),
  alpha ?: this.alpha(),
)
