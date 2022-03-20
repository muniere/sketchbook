package net.muniere.sketchbook.orb.pathFinding

import net.muniere.sketchbook.lib.math.Spot
import kotlin.math.pow
import kotlin.math.sqrt

internal object HeuristicModels {

  internal class Euclid : HeuristicModel {
    override fun estimate(a: NodeModel, b: NodeModel): Float {
      return sqrt(
        (a.spot.row - b.spot.row).toFloat().pow(2) + (a.spot.column - b.spot.column).toFloat().pow(2)
      )
    }
  }

  internal class Manhattan : HeuristicModel {
    override fun estimate(a: NodeModel, b: NodeModel): Float {
      return Spot.dist(a.spot, b.spot).toFloat()
    }
  }
}
