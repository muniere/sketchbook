package net.muniere.sketchbook.orb.lifeGame

import net.muniere.sketchbook.lib.math.Dimen
import net.muniere.sketchbook.lib.math.Matrix
import net.muniere.sketchbook.lib.math.Spot

internal final class GridModel(
  private val matrix: Matrix<CellModel>,
) {

  public companion object {
    public fun generate(dimen: Dimen, factory: (spot: Spot) -> CellModel): GridModel {
      return GridModel(
        matrix = Matrix.generate(dimen, factory)
      )
    }
  }

  public val width: Int
    get() = this.matrix.width

  public val height: Int
    get() = this.matrix.height

  public fun walk(callback: (model: CellModel, spot: Spot) -> Unit) {
    this.matrix.forEachIndexed(callback)
  }

  public fun next(): GridModel {
    return GridModel.generate(this.matrix.dimen) { spot ->
      val (row, column) = spot
      val current = this.matrix.get(spot)

      val neighbors = listOfNotNull(
        this.matrix.getOrNull(row = row - 1, column = column - 1),
        this.matrix.getOrNull(row = row - 1, column = column + 0),
        this.matrix.getOrNull(row = row - 1, column = column + 1),
        this.matrix.getOrNull(row = row + 0, column = column - 1),
        this.matrix.getOrNull(row = row + 0, column = column + 1),
        this.matrix.getOrNull(row = row + 1, column = column - 1),
        this.matrix.getOrNull(row = row + 1, column = column + 0),
        this.matrix.getOrNull(row = row + 1, column = column + 1),
      )

      val density = neighbors.count { it == CellModel.ALIVE }

      return@generate when (current) {
        CellModel.ALIVE -> when (density) {
          2 -> CellModel.ALIVE
          3 -> CellModel.ALIVE
          else -> CellModel.DEAD
        }
        CellModel.DEAD -> when (density) {
          3 -> CellModel.ALIVE
          else -> CellModel.DEAD
        }
      }
    }
  }
}
