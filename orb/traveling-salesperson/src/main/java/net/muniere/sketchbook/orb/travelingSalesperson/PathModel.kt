package net.muniere.sketchbook.orb.travelingSalesperson

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.sumOf

internal final class PathModel(
  points: List<Point2D>,
) {
  public val points: List<Point2D>
    get() = this._points

  private var _points: MutableList<Point2D> = points.toMutableList()
  private var _length: Float = -1.0F

  public fun measure(): Float {
    if (this._length >= 0) {
      return this._length
    }

    val length = this._points.zipWithNext().sumOf { (a, b) -> Point2D.dist(a, b) }
    this._length = length
    return length
  }

  public fun noise() {
    val (i, j) = this._points.indices.shuffled()

    val v = this._points[i]
    this._points[i] = this._points[j]
    this._points[j] = v
  }
}
