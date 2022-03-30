package net.muniere.sketchbook.orb.langtonAnt

internal final class ApplicationModel(
  public val ants: List<AntModel>,
  public val grid: GridModel,
) {
  public var step = StepModel(0)
    private set

  public fun update() {
    val grid = this.grid
    val dimen = grid.dimen

    this.ants.forEach { ant ->
      val spot = ant.spot
      val cell = grid.getOrNull(spot) ?: run {
        return@forEach
      }

      when (cell) {
        CellModel.WHITE -> {
          ant.turnRight()
          grid.flip(spot)
          ant.forward()
          ant.cycleIn(dimen)
        }
        CellModel.BLACK -> {
          ant.turnLeft()
          grid.flip(spot)
          ant.forward()
          ant.cycleIn(dimen)
        }
      }
    }

    this.step.value += 1
  }
}
