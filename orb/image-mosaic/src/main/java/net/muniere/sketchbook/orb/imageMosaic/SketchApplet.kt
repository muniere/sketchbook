package net.muniere.sketchbook.orb.imageMosaic

import android.graphics.BitmapFactory
import android.net.Uri
import net.muniere.sketchbook.lib.math.Dimen
import net.muniere.sketchbook.lib.processing.SApplet
import processing.core.PImage
import java.io.File

internal final class SketchApplet(
  private val uri: Uri,
) : SApplet() {

  private object Params {
    internal const val PATCH_DIR = "mosaic"
    internal const val PATCH_SIZE = 8
    internal const val PATCH_LOADING_CHUNK = 16
    internal const val PATCH_LOADING_SLEEP = 1000L
  }

  private lateinit var image: PImage
  private lateinit var model: MosaicProcessModel

  private var mosaic: MosaicImageModel? = null

  override fun doSetup() {
    super.doSetup()

    this.image = when (this.uri.toString().startsWith("content:")) {
      true -> this.context.contentResolver.openInputStream(this.uri).use {
        PImage(BitmapFactory.decodeStream(it))
      }
      false -> this.loadImage(this.uri.toString())
    }

    this.model = MosaicProcessModel(
      graphics = this.g,
      evaluation = EvaluationModels.Brightness(this.g)
    )

    val loader = PatchImageLoader(this.context.assets)
    val dimen = Dimen.square(Params.PATCH_SIZE)
    val paths = this.context.assets.list(Params.PATCH_DIR).orEmpty()
      .filterNot { it.startsWith(".") }
      .map { File(Params.PATCH_DIR, it).path }

    kotlin.concurrent.thread {
      paths.shuffled()
        .chunked(Params.PATCH_LOADING_CHUNK)
        .forEach { chunk ->
          Thread.sleep(Params.PATCH_LOADING_SLEEP)

          val patches = chunk.mapNotNull {
            loader.load(it, dimen)
          }

          this.model.addAll(patches)

          this.mosaic = this.model.convert(
            image = this.image,
            scale = Params.PATCH_SIZE,
          )

          this.loop()
        }
    }
  }

  override fun doDraw() {
    when (val mosaic = this.mosaic) {
      null -> {
        this.image.loadPixels()

        this.image(this.image, 0.0F, 0.0F)
      }
      else -> {
        mosaic.forEachIndexed { patch, spot ->
          val x = (spot.column * Params.PATCH_SIZE).toFloat()
          val y = (spot.row * Params.PATCH_SIZE).toFloat()
          this.image(patch.image, x, y)
        }
      }
    }

    this.noLoop()
  }
}
