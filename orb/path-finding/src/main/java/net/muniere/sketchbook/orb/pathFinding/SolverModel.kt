package net.muniere.sketchbook.orb.pathFinding

internal final class SolverModel(
  public val graph: GraphModel,
  public val heuristic: HeuristicModel,
) {
  internal enum class State {
    RUNNING,
    SUCCESS,
    FAILURE,
  }

  public val openSet: Set<NodeModel>
    get() = this._openSet

  private var _openSet = setOf<NodeModel>(graph.first())

  public val closedSet: Set<NodeModel>
    get() = this._closedSet

  private var _closedSet = setOf<NodeModel>()

  public val answer: List<NodeModel>
    get() = this._answer

  private var _answer = listOf<NodeModel>()

  public val state: State
    get() {
      if (this._openSet.isEmpty()) {
        return State.FAILURE
      }

      val terminal = this.graph.last()
      val current = this._openSet.minByOrNull { it.cost.f }
      return when (current) {
        terminal -> State.SUCCESS
        else -> State.RUNNING
      }
    }

  public fun next() {
    val terminal = this.graph.last()
    val current = this._openSet.minByOrNull { it.cost.f }

    if (current == null || current == terminal) {
      return
    }

    this._openSet = this._openSet.filter { it != current }.toSet()
    this._closedSet = this._closedSet + current

    this.graph.getNeighbors(current.spot)
      .filterNot { this._closedSet.contains(it) }
      .forEach { neighbor ->
        val weight = 1
        val newCost = current.cost.g + weight

        var newPathFound = false

        when (this._openSet.contains(neighbor)) {
          true -> {
            newPathFound = newCost < neighbor.cost.g
            neighbor.cost.g = neighbor.cost.g.coerceAtMost(newCost)
          }
          false -> {
            newPathFound = true
            neighbor.cost.g = newCost
            this._openSet = this._openSet + neighbor
          }
        }

        if (newPathFound) {
          neighbor.previous = current
          neighbor.cost.h = this.heuristic.estimate(neighbor, terminal)
        }
      }

    this._answer = current.trace()
  }
}
