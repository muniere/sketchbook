package net.muniere.sketchbook.orb.travelingSalesperson.genetic

import net.muniere.sketchbook.lib.math.Fraction
import net.muniere.sketchbook.orb.travelingSalesperson.PathModel

internal final class SolverModel(
  private val group: PathGroupModel,
  private val limit: Int,
) {
  public val paths: List<PathModel>
    get() = this.group.paths

  public var answer: PathModel? = null
    private set

  public val progress: Fraction
    get() = Fraction(
      numerator = this.group.generation,
      denominator = this.limit,
    )

  public fun hasNext(): Boolean {
    return this.group.generation < this.limit
  }

  public fun next() {
    this.group.cycle()

    val path = this.group.best() ?: run {
      return
    }

    when (val answer = this.answer) {
      null -> {
        this.answer = path
      }
      else -> {
        if (path.measure() < answer.measure()) {
          this.answer = path
        }
      }
    }
  }
}
