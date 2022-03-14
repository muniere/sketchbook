package net.muniere.sketchbook.orb.fourierTransform.io

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PointSchema(
  @SerialName("x")
  public val x: Double,
  @SerialName("y")
  public val y: Double,
)
