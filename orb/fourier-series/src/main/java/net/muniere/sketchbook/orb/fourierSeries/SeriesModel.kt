package net.muniere.sketchbook.orb.fourierSeries

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.processing.FrameClock

internal final class SeriesModel(
  public val circles: List<CircleModel>,
) {

  public companion object {
    public fun create(amplitude: Float, depth: Int): SeriesModel {
      val circles = List(depth) { i ->
        val n = i * 2 + 1
        return@List CircleModel(
          center = Point2D.zero(),
          radius = amplitude / n,
        )
      }

      return SeriesModel(circles)
    }
  }

  public var color: Color
    get() = this.circles.first().color
    set(value) = this.circles.forEach { it.color = value }

  public fun first(): CircleModel {
    return this.circles.first()
  }

  public fun last(): CircleModel {
    return this.circles.last()
  }

  public fun update(clock: FrameClock) {
    val t = clock.time

    var center = Point2D.zero()

    this.circles.forEachIndexed { i, circle ->
      val n = i * 2 + 1
      circle.center = center
      circle.angle = n * t
      center = circle.epicycleCenter()
    }
  }
}
