package net.muniere.sketchbook.orb.fourierTransform

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D

internal final class PathModel(
  points: List<Point2D> = emptyList(),
) {
  public val points: List<Point2D>
    get() = this._points

  private val _points = points.toMutableList()

  public var color: Color = Colors.WHITE
  public var maxLength: Int = -1

  public fun isEmpty(): Boolean {
    return this._points.isEmpty()
  }

  public fun first(): Point2D {
    return this._points.first()
  }

  public fun last(): Point2D {
    return this._points.last()
  }

  public fun push(plot: Point2D) {
    this._points.add(plot)

    if (this.maxLength > 0 && this._points.size > this.maxLength) {
      this._points.removeFirst()
    }
  }
}
