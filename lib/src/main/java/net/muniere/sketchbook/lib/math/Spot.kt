package net.muniere.sketchbook.lib.math

import kotlin.math.absoluteValue

public final data class Spot(
  public val row: Int,
  public val column: Int,
) {
  public companion object {
    public fun dist(a: Spot, b: Spot): Int {
      return (a.row - b.row).absoluteValue + (a.column - b.column).absoluteValue
    }
  }

  public fun shiftedBy(shift: Shift): Spot {
    return Spot(
      row = this.row + shift.row,
      column = this.column + shift.column,
    )
  }
}
