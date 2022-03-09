package net.muniere.sketchbook.orb.circlePacking

import net.muniere.sketchbook.lib.drawing.Pixel2D
import net.muniere.sketchbook.lib.drawing.pixel2Ds
import processing.core.PImage

internal class PixelCrowdModel(
  pixels: List<Pixel2D>,
) {

  public val pixels: List<Pixel2D>
    get() = this.values

  private var values = pixels.toMutableList()

  public companion object {

    public fun analyze(image: PImage, predicate: (pixel: Pixel2D) -> Boolean): PixelCrowdModel {
      return PixelCrowdModel(image.pixel2Ds.filter(predicate))
    }
  }

  public fun random(): Pixel2D {
    return this.values.random()
  }

  public fun removeIf(predicate: (pixel: Pixel2D) -> Boolean) {
    this.values.removeIf(predicate)
  }

  public fun translate(x: Int = 0, y: Int = 0) {
    this.values.forEach {
      it.point.x += x
      it.point.y += y
    }
  }

  public fun scale(sx: Float = 1.0F, sy: Float = 1.0F) {
    this.values.forEach {
      it.point.x *= sx
      it.point.y *= sy
    }
  }
}
