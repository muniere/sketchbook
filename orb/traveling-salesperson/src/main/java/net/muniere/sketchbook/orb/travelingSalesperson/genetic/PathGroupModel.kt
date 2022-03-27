package net.muniere.sketchbook.orb.travelingSalesperson.genetic

import net.muniere.sketchbook.orb.travelingSalesperson.PathModel
import kotlin.random.Random

internal final class PathGroupModel(
  paths: List<PathModel>,
  cross: CrossModel,
  mutation: MutationModel,
) {
  public var paths: List<PathModel> = paths
    private set

  public var generation: Int = 1
    private set

  public var cross: CrossModel = cross
    private set

  public var mutation: MutationModel = mutation
    private set

  public fun best(): PathModel? {
    return this.paths.minByOrNull { it.measure() }
  }

  public fun cycle() {
    val sorted = this.paths.sortedBy { it.measure() }
    val scores = sorted.map { 1.0F / (it.measure() + 1) }
    val total = scores.sum()

    val densities = scores.map { it / total }
    val cumulative = densities.toMutableList()
    (1 until cumulative.size).forEach { i ->
      cumulative[i] += cumulative[i - 1]
    }

    this.paths = sorted.map { _ ->
      val value1 = Random.nextFloat()
      val index1 = cumulative.indexOfFirst { it >= value1 }
      val parent1 = sorted[index1]

      val value2 = Random.nextFloat()
      val index2 = cumulative.indexOfFirst { it >= value2 }
      val parent2 = sorted[index2]

      val child = this.cross.perform(parent1, parent2)

      if (Random.nextFloat() > this.mutation.rate) {
        return@map this.mutation.perform(child)
      } else {
        return@map child
      }
    }

    this.generation += 1
  }
}
