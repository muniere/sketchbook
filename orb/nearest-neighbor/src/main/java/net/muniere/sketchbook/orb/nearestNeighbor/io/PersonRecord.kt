package net.muniere.sketchbook.orb.nearestNeighbor.io

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PersonRecord(
  @SerialName("name")
  internal val name: String,
  @SerialName("I")
  internal val I: Int? = null,
  @SerialName("II")
  internal val II: Int? = null,
  @SerialName("III")
  internal val III: Int? = null,
  @SerialName("IV")
  internal val IV: Int? = null,
  @SerialName("V")
  internal val V: Int? = null,
  @SerialName("VI")
  internal val VI: Int? = null,
  @SerialName("VII")
  internal val VII: Int? = null,
  @SerialName("Rogue1")
  internal val rogue1: Int? = null,
  @SerialName("Holiday")
  internal val holiday: Int? = null,
)
