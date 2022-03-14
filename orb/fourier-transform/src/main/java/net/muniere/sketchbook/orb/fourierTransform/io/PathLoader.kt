package net.muniere.sketchbook.orb.fourierTransform.io

import android.content.res.AssetManager
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal final class PathLoader(
  private val assets: AssetManager,
) {

  private object Const {
    internal const val FILE_NAME = "path.json"
  }

  public fun load(interval: Int = 1): PathSchema {
    val text = this.assets.open(Const.FILE_NAME).bufferedReader().readText()
    val points = Json.decodeFromString<List<PointSchema>>(text)

    return PathSchema(
      points = points.filterIndexed { index, _ -> index % interval == 0 }
    )
  }
}
