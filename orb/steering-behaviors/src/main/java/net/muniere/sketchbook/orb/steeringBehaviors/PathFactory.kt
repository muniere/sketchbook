package net.muniere.sketchbook.orb.steeringBehaviors

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.Typeface
import net.muniere.sketchbook.lib.graphics.Move2D
import net.muniere.sketchbook.lib.graphics.Path2D
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.sample
import kotlin.math.min

internal final class PathFactory {

  private val paint = Paint()

  public var textSize: Float
    get() {
      return this.paint.textSize
    }
    set(value) {
      this.paint.textSize = value
    }

  public var typeFace: Typeface
    get() {
      return this.paint.typeface
    }
    set(value) {
      this.paint.typeface = value
    }

  public var stepSize: Float = 1.0F

  public fun analyze(text: String): Path2D {
    val path = Path().also {
      this.paint.getTextPath(text, 0, text.length, 0.0F, 0.0F, it)
    }

    val points = PathMeasure(path, true).sample(this.stepSize).map { pos ->
      Point2D(pos[0], pos[1])
    }

    val offset = Move2D(
      x = Float.MAX_VALUE,
      y = Float.MAX_VALUE,
    )

    points.forEach {
      offset.x = min(offset.x, it.x)
      offset.y = min(offset.y, it.y)
    }

    return Path2D(
      points = points.map { it - offset }
    )
  }
}

