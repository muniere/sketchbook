package net.muniere.sketchbook.orb.ulamSpiral

import net.muniere.sketchbook.lib.atlas.Direction
import net.muniere.sketchbook.lib.atlas.Director
import net.muniere.sketchbook.lib.math.Spot

internal final class PieceModel(
  spot: Spot,
  direction: Direction,
) {
  public var spot: Spot = spot
    private set

  public var direction: Direction = direction
    private set

  public var stepCount: Int = 0
    private set

  private var stepRegister: Int = 0
  private var stepInterval: Int = 1

  public fun forward() {
    this.spot = this.spot.shiftedBy(Director.step(this.direction))
    this.stepCount += 1

    if (this.stepCount - this.stepRegister == this.stepInterval) {
      this.turn()
    }
  }

  private fun turn() {
    this.direction = Director.left(this.direction)
    this.stepRegister = this.stepCount

    when (this.direction) {
      Direction.NORTH,
      Direction.SOUTH,
      -> {
        // do nothing; keep interval
      }
      Direction.WEST,
      Direction.EAST,
      -> {
        this.stepInterval += 1
      }
    }
  }
}
