package net.muniere.sketchbook.lib.atlas

import net.muniere.sketchbook.lib.math.Shift

public final object Director {

  private val STEPS = mapOf(
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

  public fun step(direction: Direction): Shift {
    return STEPS[direction].let(::checkNotNull)
  }

  public fun left(direction: Direction): Direction {
    return LEFTS[direction].let(::checkNotNull)
  }

  public fun right(direction: Direction): Direction {
    return RIGHTS[direction].let(::checkNotNull)
  }
}
