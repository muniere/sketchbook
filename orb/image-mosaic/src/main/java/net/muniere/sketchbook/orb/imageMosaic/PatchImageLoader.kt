package net.muniere.sketchbook.orb.imageMosaic

import android.content.res.AssetManager
import android.graphics.BitmapFactory
import net.muniere.sketchbook.lib.math.Dimen
import processing.core.PConstants
import processing.core.PImage

internal final class PatchImageLoader(
  private val assets: AssetManager,
) {

  public fun load(path: String, dimen: Dimen): PImage? {
    val measuringOptions = BitmapFactory.Options().also {
      it.inJustDecodeBounds = true
    }
    this.assets.open(path).use {
      BitmapFactory.decodeStream(it, null, measuringOptions)
    }

    val loadingOptions = BitmapFactory.Options().also {
      it.inSampleSize = minOf(
        measuringOptions.outWidth / dimen.width,
        measuringOptions.outHeight / dimen.height,
      )
    }

    val bitmap = this.assets.open(path).use {
      BitmapFactory.decodeStream(it, null, loadingOptions)
    } ?: run {
      return null
    }

    val source = PImage(bitmap)

    val patch = PImage(dimen.width, dimen.height, PConstants.ARGB).also {
      it.copy(
        source,
        0, 0, source.width, source.height,
        0, 0, it.width, it.height,
      )
    }

    bitmap.recycle()

    return patch
  }
}
