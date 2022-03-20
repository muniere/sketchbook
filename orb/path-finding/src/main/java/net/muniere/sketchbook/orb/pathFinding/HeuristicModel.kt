package net.muniere.sketchbook.orb.pathFinding

internal fun interface HeuristicModel {
  public fun estimate(a: NodeModel, b: NodeModel): Float
}
