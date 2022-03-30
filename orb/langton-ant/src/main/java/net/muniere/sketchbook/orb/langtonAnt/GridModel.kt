package net.muniere.sketchbook.orb.langtonAnt

import net.muniere.sketchbook.lib.math.Dimen
import net.muniere.sketchbook.lib.math.Matrix
import net.muniere.sketchbook.lib.math.Spot

internal final class GridModel(
  private val cells: Matrix<CellModel>,
) {

  public companion object {
    public fun create(dimen: Dimen): GridModel {
      return GridModel(
        cells = Matrix.fill(dimen, CellModel.WHITE)
      )
    }
  }

  public val dimen: Dimen
    get() = this.cells.dimen

  public fun get(spot: Spot): CellModel {
    return this.cells.get(spot)
  }

  public fun getOrNull(spot: Spot): CellModel? {
    return this.cells.getOrNull(spot)
  }

  public fun forEachIndexed(action: (cell: CellModel, spot: Spot) -> Unit) {
    this.cells.forEachIndexed(action)
  }

  public fun flip(spot: Spot) {
    val cell = this.cells.getOrNull(spot) ?: run {
      return
    }

    val newValue = when (cell) {
      CellModel.WHITE -> CellModel.BLACK
      CellModel.BLACK -> CellModel.WHITE
    }

    this.cells.set(spot, newValue)
  }
}
