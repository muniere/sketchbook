package net.muniere.sketchbook.orb.langtonAnt

import net.muniere.sketchbook.lib.math.Dimen
import net.muniere.sketchbook.lib.atlas.Direction
import net.muniere.sketchbook.lib.atlas.Director
import net.muniere.sketchbook.lib.math.Spot

internal final class AntModel(
  spot: Spot,
  direction: Direction,
) {
  public var spot: Spot = spot
    private set

  public var direction: Direction = direction
    private set

  public fun forward() {
    this.spot = this.spot.shiftedBy(
      Director.step(this.direction)
    )
  }

  public fun cycleIn(dimen: Dimen) {
    this.spot = dimen.cycle(this.spot)
  }

  public fun turnLeft() {
    this.direction = Director.left(this.direction)
  }

  public fun turnRight() {
    this.direction = Director.right(this.direction)
  }
}
