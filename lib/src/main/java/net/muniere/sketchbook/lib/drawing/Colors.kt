package net.muniere.sketchbook.lib.drawing

import android.graphics.Color
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
