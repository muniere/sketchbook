package net.muniere.sketchbook.orb.imageMosaic

import net.muniere.sketchbook.lib.math.Dimen
import net.muniere.sketchbook.lib.math.Matrix
import processing.core.PGraphics
import processing.core.PImage
import kotlin.math.absoluteValue

internal final class MosaicProcessModel(
  private val graphics: PGraphics,
  private val evaluation: EvaluationModel,
) {
  public val patches: List<PatchImageModel>
    get() = this._patches

  private val _patches = mutableListOf<PatchImageModel>()

  private var _table: Map<Int, PatchImageModel>? = null

  public fun add(image: PImage) {
    val patch = PatchImageModel(
      image = image,
      value = this.evaluation.apply(image),
    )

    this._patches.add(patch)
    this._table = null
  }

  public fun addAll(images: List<PImage>) {
    val patches = images.map {
      PatchImageModel(
        image = it,
        value = this.evaluation.apply(it),
      )
    }

    this._patches.addAll(patches)
    this._table = null
  }

  public fun convert(image: PImage, scale: Int): MosaicImageModel {
    if (this._table == null) {
      val t = this.evaluation.range.associateWith { value ->
        val v = this._patches.minByOrNull { (it.value - value).absoluteValue }.let(::checkNotNull)
        return@associateWith v
      }
      this._table = t
    }

    val table = checkNotNull(this._table)

    val dimen = Dimen(
      image.width / scale,
      image.height / scale,
    )

    val scaled = PImage(dimen.width, dimen.height).also {
      it.copy(
        image,
        0, 0, image.width, image.height,
        0, 0, it.width, it.height,
      )
    }

    val patches = Matrix.generate(dimen) { spot ->
      val pixel = scaled.pixels[spot.row * dimen.width + spot.column]
      val value = this.evaluation.apply(pixel)
      return@generate table[value].let(::checkNotNull)
    }

    return MosaicImageModel(patches)
  }
}
