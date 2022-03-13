package net.muniere.sketchbook.orb.imageDithering

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.ColorDiff
import net.muniere.sketchbook.lib.drawing.plus
import net.muniere.sketchbook.lib.drawing.quantize
import processing.core.PImage

internal final class ImageMachineModel(
  public val image: PImage,
  public val scale: Int,
) {

  public var cursor: Int = 0
    private set

  public var speed: Int = 0

  public val hasNext: Boolean
    get() = this.cursor < (this.image.width * this.image.height)

  private val relays: List<RelayModel> = listOf(
    RelayModel(dx = 1, dy = 0, rate = 7.0F / 16.0F),
    RelayModel(dx = 1, dy = 1, rate = 3.0F / 16.0F),
    RelayModel(dx = 0, dy = 1, rate = 5.0F / 16.0F),
    RelayModel(dx = 1, dy = 1, rate = 1.0F / 16.0F),
  )

  public fun dither() {
    val speed = this.speed.takeIf { it > 0 } ?: Int.MAX_VALUE

    var i = 0

    while (i < speed && this.hasNext) {
      this.doDither(this.cursor)
      this.cursor += 1
      i += 1
    }

    this.image.updatePixels()
  }

  private fun doDither(index: Int) {
    val x = index % this.image.width
    val y = index / this.image.width

    val oldValue = this.image.pixels[index]
    val oldColor = Color.valueOf(oldValue)
    val newColor = oldColor.quantize(this.scale)
    val error = ColorDiff.measure(oldColor, newColor)

    this.image.pixels[index] = newColor.toArgb()

    this.relays.forEach { relay ->
      val xx = x + relay.dx
      val yy = y + relay.dy

      if (xx < 0 || this.image.width <= xx) {
        return@forEach
      }
      if (yy < 0 || this.image.height <= yy) {
        return@forEach
      }

      val position = this.image.width * yy + xx
      val baseValue = this.image.pixels[position]
      val baseColor = Color.valueOf(baseValue)
      val nextColor = baseColor + error * relay.rate

      this.image.pixels[position] = nextColor.toArgb()
    }
  }
}
