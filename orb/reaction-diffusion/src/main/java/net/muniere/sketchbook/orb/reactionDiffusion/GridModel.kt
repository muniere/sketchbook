package net.muniere.sketchbook.orb.reactionDiffusion

import net.muniere.sketchbook.lib.math.Dimen
import net.muniere.sketchbook.lib.math.Matrix
import net.muniere.sketchbook.lib.math.Spot

internal final class GridModel private constructor(
  private val matrix: Matrix<CellModel>,
) {

  internal fun interface Factory {
    public fun create(spot: Spot): CellModel
  }

  public companion object {
    public fun generate(dimen: Dimen, factory: Factory): GridModel {
      val matrix = Matrix.generate(dimen) { spot ->
        factory.create(spot)
      }
      return GridModel(matrix)
    }
  }

  public val dimen: Dimen
    get() = this.matrix.dimen

  public val width: Int
    get() = this.matrix.width

  public val height: Int
    get() = this.matrix.height

  public fun get(spot: Spot): CellModel {
    return this.matrix.get(spot)
  }

  public fun set(spot: Spot, value: CellModel) {
    val cell = this.matrix.get(spot)
    cell.a = value.a
    cell.b = value.b
  }

  public fun forEachIndexed(action: (value: CellModel, spot: Spot) -> Unit) {
    this.matrix.forEachIndexed(action)
  }

  public fun forEachIndexed(action: (value: CellModel, row: Int, column: Int) -> Unit) {
    this.matrix.forEachIndexed(action)
  }

  public fun laplace(spot: Spot): CellModel {
    val (row, column) = spot

    val cell = this.matrix.get(spot)

    var a = cell.a * -1.0F
    var b = cell.b * -1.0F

    this.matrix.get(row, column + 1).let {
      a += it.a * 0.2F
      b += it.b * 0.2F
    }
    this.matrix.get(row, column - 1).let {
      a += it.a * 0.2F
      b += it.b * 0.2F
    }
    this.matrix.get(row - 1, column).let {
      a += it.a * 0.2F
      b += it.b * 0.2F
    }
    this.matrix.get(row + 1, column).let {
      a += it.a * 0.2F
      b += it.b * 0.2F
    }
    this.matrix.get(row - 1, column - 1).let {
      a += it.a * 0.05F
      b += it.b * 0.05F
    }
    this.matrix.get(row + 1, column - 1).let {
      a += it.a * 0.05F
      b += it.b * 0.05F
    }
    this.matrix.get(row - 1, column + 1).let {
      a += it.a * 0.05F
      b += it.b * 0.05F
    }
    this.matrix.get(row + 1, column + 1).let {
      a += it.a * 0.05F
      b += it.b * 0.05F
    }

    return CellModel(a, b)
  }
}
