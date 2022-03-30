package net.muniere.sketchbook.lib.math

import kotlin.random.Random

public final data class Dimen(
  public val width: Int,
  public val height: Int,
) {
  public companion object {
    public fun zero() = Dimen(0, 0)
    public fun square(size: Int) = Dimen(size, size)
  }

  public fun cycle(spot: Spot): Spot {
    var (row, column) = spot

    if (row < 0) {
      row += this.height
    }
    if (row >= this.height) {
      row -= this.height
    }
    if (column < 0) {
      column += this.width
    }
    if (column >= this.width) {
      column -= this.width
    }

    return Spot(row, column)
  }

  public fun random(): Spot {
    return Spot(
      row = Random.nextInt(this.height),
      column = Random.nextInt(this.width),
    )
  }
}
