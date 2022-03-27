package net.muniere.sketchbook.orb.travelingSalesperson.naive

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.math.Fraction
import net.muniere.sketchbook.orb.travelingSalesperson.PathModel

internal final class SolverModel(
  private val generator: GeneratorModel,
) {

  public constructor(points: List<Point2D>) : this(GeneratorModel(points))

  public var path: PathModel? = null
    private set

  public var answer: PathModel? = null
    private set

  public var count: Int = 0
    private set

  public val progress: Fraction
    get() = Fraction(
      numerator = this.count,
      denominator = this.generator.size
    )

  public fun hasNext(): Boolean {
    return this.count < this.generator.size
  }

  public fun next() {
    val path = this.generator.next()

    this.path = path

    if (path == null) {
      return
    }

    this.count += 1

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
