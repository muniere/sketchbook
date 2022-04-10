package net.muniere.sketchbook.orb.mitosisSimulation

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.replace

internal final class ApplicationModel(
  frame: Rect2D,
  cells: List<CellModel>,
) {

  public var frame: Rect2D = frame
    private set

  public val cells: List<CellModel>
    get() = this._cells

  private val _cells = cells.toMutableList()

  public val bounds: Rect2D
    get() = this.frame.copy(
      origin = Point2D.zero(),
    )

  public fun findIndex(point: Point2D): Int {
    return this.cells.indexOfFirst { it.contains(point) }
  }

  public fun update() {
    this.cells.forEach {
      it.update()
      it.constraint(this.bounds)
    }
  }

  public fun split(index: Int) {
    val cell = this._cells[index]
    val children = cell.split()
    this._cells.replace(index, children)
  }
}
