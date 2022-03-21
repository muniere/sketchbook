package net.muniere.sketchbook.orb.lifeGame

internal final class ApplicationModel(
  grid: GridModel,
) {

  public val grid: GridModel
    get() = this._grid

  private var _grid = grid

  public fun update() {
    this._grid = this._grid.next()
  }
}
