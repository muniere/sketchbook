package net.muniere.sketchbook.orb.nearestNeighbor.domain

internal final data class AnswerModel(
  internal val prediction: RatingModel,
  internal val neighbors: List<NeighborModel>,
)
