package net.muniere.sketchbook.orb.travelingSalesperson.naive

import net.muniere.sketchbook.lib.collect
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.math.factorial
import net.muniere.sketchbook.lib.permutation
import net.muniere.sketchbook.orb.travelingSalesperson.PathModel

internal final class GeneratorModel(
  points: List<Point2D>,
) {

  public val size: Int = points.size.let(::factorial)
  public val points: List<Point2D> = points.sortedBy(Point2D::dist)
  public val iterator: Iterator<List<Int>> = points.indices.toList().permutation().iterator()

  public fun next(): PathModel? {
    if (!this.iterator.hasNext()) {
      return null
    }

    val indices = this.iterator.next()
    return PathModel(
      points = this.points.collect(indices)
    )
  }

  public fun hasNext(): Boolean {
    return this.iterator.hasNext()
  }
}
