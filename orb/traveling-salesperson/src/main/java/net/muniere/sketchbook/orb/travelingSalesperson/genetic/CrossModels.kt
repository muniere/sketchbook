package net.muniere.sketchbook.orb.travelingSalesperson.genetic

import net.muniere.sketchbook.orb.travelingSalesperson.PathModel
import kotlin.math.max
import kotlin.math.min

internal object CrossModels {

  internal final class Clone : CrossModel() {

    override fun perform(a: PathModel, b: PathModel): PathModel {
      return PathModel(
        points = a.points,
      )
    }
  }

  internal final class Joint : CrossModel() {

    override fun perform(a: PathModel, b: PathModel): PathModel {
      val i = a.points.indices.random()
      val j = a.points.indices.random()

      val start = min(i, j)
      val end = max(i, j)

      val head = a.points.subList(start, end)
      val tail = b.points.subtract(head)

      return PathModel(
        points = head + tail
      )
    }
  }
}
