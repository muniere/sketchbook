package net.muniere.sketchbook.orb.fourierSeries

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors

internal final class PathModel(
  values: List<Float> = emptyList(),
) {
  public val values: List<Float>
    get() = this._values

  private val _values = values.toMutableList()

  public var color: Color = Colors.WHITE
  public var maxLength: Int = -1

  public fun first(): Float {
    return this.values.first()
  }

  public fun last(): Float {
    return this.values.last()
  }

  public fun push(value: Float) {
    this._values.add(0, value)

    if (this.maxLength > 0 && this._values.size > this.maxLength) {
      this._values.removeLast()
    }
  }
}
