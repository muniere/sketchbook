package net.muniere.sketchbook.orb.nearestNeighbor.domain

internal final data class NeighborModel(
  private val person: PersonModel,
  internal val similarity: Double,
) {
  internal val name: String
    get() = this.person.name

  internal val rating: RatingModel
    get() = this.person.rating
}
