package net.muniere.sketchbook.orb.nearestNeighbor.io

import android.content.res.AssetManager
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal final class DataLoader(
  private val assets: AssetManager,
) {

  private object Const {
    internal const val FILE_NAME = "data.json"
  }

  public fun load(): List<PersonRecord> {
    val text = this.assets.open(Const.FILE_NAME).bufferedReader().readText()
    val codec = Json {
      ignoreUnknownKeys = true
    }
    return codec.decodeFromString(text)
  }
}
