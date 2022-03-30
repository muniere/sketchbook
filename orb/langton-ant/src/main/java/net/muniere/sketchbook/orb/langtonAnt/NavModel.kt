package net.muniere.sketchbook.orb.langtonAnt

import net.muniere.sketchbook.lib.math.Shift

internal final object NavModel {

  private val SHIFTS = mapOf(
    Direction.NORTH to Shift(row = -1, column = 0),
    Direction.SOUTH to Shift(row = +1, column = 0),
    Direction.WEST to Shift(row = 0, column = -1),
    Direction.EAST to Shift(row = 0, column = +1),
  )

  private val LEFTS = mapOf(
    Direction.NORTH to Direction.WEST,
    Direction.SOUTH to Direction.EAST,
    Direction.WEST to Direction.SOUTH,
    Direction.EAST to Direction.NORTH,
  )

  private val RIGHTS = mapOf(
    Direction.NORTH to Direction.EAST,
    Direction.SOUTH to Direction.WEST,
    Direction.WEST to Direction.NORTH,
    Direction.EAST to Direction.SOUTH,
  )

  public fun jump(direction: Direction): Shift {
    return this.SHIFTS[direction].let(::checkNotNull)
  }

  public fun left(direction: Direction): Direction {
    return this.LEFTS[direction].let(::checkNotNull)
  }

  public fun right(direction: Direction): Direction {
    return this.RIGHTS[direction].let(::checkNotNull)
  }
}
