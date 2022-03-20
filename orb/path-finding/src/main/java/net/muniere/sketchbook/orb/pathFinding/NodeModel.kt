package net.muniere.sketchbook.orb.pathFinding

import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.math.Spot

internal final class NodeModel(
  public var kind: Kind,
  public var spot: Spot,
  public var size: Size2D,
  public var cost: CostModel,
) {
  internal enum class Kind {
    PATH,
    WALL,
  }

  public var previous: NodeModel? = null

  public fun trace(): List<NodeModel> {
    val chain = mutableListOf<NodeModel>()

    var cursor: NodeModel = this

    while (cursor.previous != null) {
      chain.add(cursor)
      cursor = cursor.previous.let(::checkNotNull)
    }

    chain.add(cursor)

    return chain
  }
}
