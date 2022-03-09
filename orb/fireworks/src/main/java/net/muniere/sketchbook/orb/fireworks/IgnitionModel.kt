package net.muniere.sketchbook.orb.fireworks

import android.graphics.Color
import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.physics.CircularMaterial
import net.muniere.sketchbook.lib.physics.Velocity2D
import kotlin.random.Random

internal interface IgnitionModel {
  public fun performIn(rect: Rect2D): FireSeedModel
}

internal class RandomIgnitionModel : IgnitionModel {
  public var radiusRange = FloatRange(0.0F, 0.0F)
  public var speedRange = FloatRange(0.0F, 0.0F)
  public var lifespanRange = IntRange(0, 0)

  override fun performIn(rect: Rect2D): FireSeedModel {
    val xs = FloatRange(rect.left, rect.right)

    val color = Color.valueOf(
      Random.nextFloat(),
      Random.nextFloat(),
      Random.nextFloat(),
      1.0F,
    )

    return FireSeedModel(
      core = CircularMaterial(
        radius = this.radiusRange.random(),
        center = Point2D(
          x = xs.random(),
          y = rect.bottom,
        ),
        velocity = Velocity2D(
          x = 0.0F,
          y = this.speedRange.random(),
        )
      ).also {
        it.fillColor = color
      },
      lifespan = this.lifespanRange.random(),
    )
  }
}
