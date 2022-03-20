package net.muniere.sketchbook.orb.pathFinding

import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.math.Dimen
import net.muniere.sketchbook.lib.math.Matrix
import net.muniere.sketchbook.lib.math.Spot

internal final class GraphModel(
  public val nodes: Matrix<NodeModel>,
) {

  companion object {
    public fun generate(rect: Rect2D, dimen: Dimen, kind: (spot: Spot) -> NodeModel.Kind): GraphModel {
      val itemWidth = rect.width / dimen.width
      val itemHeight = rect.height / dimen.height

      val nodes = Matrix.generate(dimen) { spot ->
        NodeModel(
          kind = kind(spot),
          spot = spot,
          size = Size2D(itemWidth, itemHeight),
          cost = CostModel.zero(),
        )
      }

      return GraphModel(nodes)
    }
  }

  public fun get(spot: Spot): NodeModel {
    return this.nodes.get(spot)
  }

  public fun getOrNull(spot: Spot): NodeModel? {
    return this.nodes.getOrNull(spot)
  }

  public fun getNeighbors(spot: Spot): List<NodeModel> {
    val (row, column) = spot

    val spots = listOf(
      Spot(row, column - 1),
      Spot(row, column + 1),
      Spot(row - 1, column),
      Spot(row + 1, column),
      Spot(row - 1, column - 1),
      Spot(row - 1, column + 1),
      Spot(row + 1, column - 1),
      Spot(row + 1, column + 1),
    )

    return spots.mapNotNull(this::getOrNull).filter { it.kind == NodeModel.Kind.PATH }
  }

  public fun first(): NodeModel {
    return this.nodes.first()
  }

  public fun last(): NodeModel {
    return this.nodes.last()
  }

  public fun walk(callback: (node: NodeModel) -> Unit) {
    this.nodes.forEach(callback)
  }
}
