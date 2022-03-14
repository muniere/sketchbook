package net.muniere.sketchbook.orb.fourierTransform

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.math.Complex
import net.muniere.sketchbook.lib.processing.FrameClock
import net.muniere.sketchbook.orb.fourierTransform.math.FourierFormula

internal final class SeriesModel(
  public val circles: List<CircleModel>,
) {

  public companion object {
    public fun create(
      center: Point2D,
      values: List<Complex>,
      decorate: (CircleModel) -> Unit = { },
    ): SeriesModel {
      val N = Complex(re = values.size.toDouble())

      val circles = FourierFormula.apply(values)
        .mapIndexed { k, X ->
          CircleSeed.create(k = k, X = X / N)
        }
        .map { genome ->
          CircleModel(center, genome).also(decorate)
        }

      return SeriesModel(
        circles = circles.sortedByDescending { it.seed.amplitude }
      )
    }
  }

  public fun first(): CircleModel {
    return this.circles.first()
  }

  public fun last(): CircleModel {
    return this.circles.last()
  }

  public fun update(clock: FrameClock, offset: Float) {
    val t = clock.time

    var center = this.circles.first().center

    this.circles.forEach { circle ->
      val freq = circle.seed.frequency
      val phase = circle.seed.phase
      circle.center = center
      circle.angle = freq * t + phase + offset
      center = circle.epicycleCenter()
    }
  }
}
