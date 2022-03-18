package net.muniere.sketchbook.orb.nearestNeighbor.domain

internal final class SolverModel(
  private val people: List<PersonModel>,
) {

  public fun solve(base: PersonModel): AnswerModel {
    val neighbors = this.people.filter { it.name != base.name }.map {
      NeighborModel(
        person = it,
        similarity = RatingModel.similarity(base.rating, it.rating)
      )
    }

    val similarity = neighbors.sumOf { it.similarity }

    val prediction = RatingModel(
      I = base.rating.I ?: neighbors.sumOf { (it.rating.I ?: 0.0) * it.similarity } / similarity,
      II = base.rating.II ?: neighbors.sumOf { (it.rating.II ?: 0.0) * it.similarity } / similarity,
      III = base.rating.III ?: neighbors.sumOf { (it.rating.III ?: 0.0) * it.similarity } / similarity,
      IV = base.rating.IV ?: neighbors.sumOf { (it.rating.IV ?: 0.0) * it.similarity } / similarity,
      V = base.rating.V ?: neighbors.sumOf { (it.rating.V ?: 0.0) * it.similarity } / similarity,
      VI = base.rating.VI ?: neighbors.sumOf { (it.rating.VI ?: 0.0) * it.similarity } / similarity,
      VII = base.rating.VII ?: neighbors.sumOf { (it.rating.VII ?: 0.0) * it.similarity } / similarity,
      rogue1 = base.rating.rogue1 ?: neighbors.sumOf { (it.rating.rogue1 ?: 0.0) * it.similarity } / similarity,
      holiday = base.rating.holiday ?: neighbors.sumOf { (it.rating.holiday ?: 0.0) * it.similarity } / similarity,
    )

    return AnswerModel(
      prediction = prediction,
      neighbors = neighbors.sortedByDescending { it.similarity }.take(5)
    )
  }
}
