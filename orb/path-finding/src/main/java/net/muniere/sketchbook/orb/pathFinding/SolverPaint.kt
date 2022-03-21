package net.muniere.sketchbook.orb.pathFinding

import android.graphics.Color

internal class SolverPaint(
  private val solver: SolverModel,
  private val palette: GraphPalette,
) {

  public fun selectColor(node: NodeModel): Color {
    if (node.kind == NodeModel.Kind.WALL) {
      return this.palette.wallColor
    }
    if (this.solver.answer.contains(node)) {
      return when (solver.state) {
        SolverModel.State.RUNNING -> this.palette.runningColor
        SolverModel.State.FAILURE -> this.palette.abortColor
        SolverModel.State.SUCCESS -> this.palette.answerColor
      }
    }
    if (this.solver.closedSet.contains(node)) {
      return this.palette.closedColor
    }
    if (this.solver.openSet.contains(node)) {
      return this.palette.openColor
    }
    return this.palette.baseColor
  }
}
