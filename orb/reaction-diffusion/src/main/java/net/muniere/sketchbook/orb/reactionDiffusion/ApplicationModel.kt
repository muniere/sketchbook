package net.muniere.sketchbook.orb.reactionDiffusion

internal final class ApplicationModel(
  grid: GridModel,
  diffusion: DiffusionModel,
) {

  internal val grid: GridModel
    get() = this._grid

  private var _grid: GridModel = grid

  private var _work: GridModel = GridModel.generate(grid.dimen, GridFactories.Empty())

  internal val diffusion: DiffusionModel
    get() = this._diffusion

  private val _diffusion: DiffusionModel = diffusion

  public fun update(speed: Int = 1) {
    repeat(speed) {
      this._diffusion.forward(this._grid, this._work)

      val temp = this._work
      this._work = this._grid
      this._grid = temp
    }
  }
}
