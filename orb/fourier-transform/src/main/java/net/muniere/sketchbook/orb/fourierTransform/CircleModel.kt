package net.muniere.sketchbook.orb.fourierTransform

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Move2D
import net.muniere.sketchbook.lib.graphics.Point2D
import kotlin.math.cos
import kotlin.math.sin

internal final class CircleModel(
  public var center: Point2D,
  public var seed: CircleSeed,
) {
  public var color: Color = Colors.WHITE
  public var angle: Float = 0.0F

  public val radius: Float
    get() = this.seed.amplitude

  public fun epicycleCenter(): Point2D {
    return Point2D(
      x = this.center.x + this.radius * cos(this.angle),
      y = this.center.y + this.radius * sin(this.angle),
    )
  }

  public fun point(radian: Float): Point2D {
    return this.center + Move2D(
      x = this.radius * cos(radian),
      y = this.radius * sin(radian),
    )
  }
}
