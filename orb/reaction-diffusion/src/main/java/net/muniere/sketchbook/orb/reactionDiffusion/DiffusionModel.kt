package net.muniere.sketchbook.orb.reactionDiffusion

internal final class DiffusionModel(
  public val a: Float,
  public val b: Float,
  public val feed: Float,
  public val kill: Float,
) {

  public fun forward(grid: GridModel, result: GridModel) {
    grid.forEachIndexed { cell, spot ->
      if (spot.column <= 0 || grid.width - 1 <= spot.column) {
        result.get(spot).also {
          it.a = cell.a
          it.b = cell.b
        }
        return@forEachIndexed
      }

      if (spot.row <= 0 || grid.height - 1 <= spot.row) {
        result.get(spot).also {
          it.a = cell.a
          it.b = cell.b
        }
        return@forEachIndexed
      }

      val (a, b) = cell
      val lap = grid.laplace(spot)

      result.get(spot).also {
        it.a = a + (this.a * lap.a) - (a * b * b) + (this.feed * (1.0F - a))
        it.b = b + (this.b * lap.b) + (a * b * b) - (this.kill + this.feed) * b
      }
    }
  }
}
