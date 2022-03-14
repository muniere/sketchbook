package net.muniere.sketchbook.orb.fourierTransform.io

internal final data class PathSchema(
  public val points: List<PointSchema>
) {
  public val size: Int
    get() = this.points.size

  public fun <T> map(transform: (PointSchema) -> T) = this.points.map(transform)
}
