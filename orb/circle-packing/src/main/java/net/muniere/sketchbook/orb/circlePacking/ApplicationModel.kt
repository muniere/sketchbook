package net.muniere.sketchbook.orb.circlePacking

import net.muniere.sketchbook.lib.drawing.Pixel2D
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import processing.core.PImage

internal class ApplicationModel(
  public val pixelCrowd: PixelCrowdModel,
  public val circleCrowd: CircleCrowdModel,
) {
  public var spawnChallenge: Int = 0
  public var spawnRadius: Float = 0.0F
  public var spawnSpeed: Int = 0

  companion object {
    public fun create(
      frame: Rect2D,
      image: PImage,
      predicate: (pixel: Pixel2D) -> Boolean,
    ): ApplicationModel {
      val scale = minOf(
        frame.width / image.width,
        frame.height / image.height,
      )

      val scaledWidth = image.width * scale
      val scaledHeight = image.height * scale

      val originX = (frame.width - scaledWidth) / 2
      val originY = (frame.height - scaledHeight) / 2

      val pixelCrowd = PixelCrowdModel.analyze(image, predicate).also {
        it.scale(sx = scale, sy = scale)
        it.translate(x = originX.toInt(), y = originY.toInt())
      }

      val circleCrowd = CircleCrowdModel(
        frame = Rect2D(
          origin = Point2D.zero(),
          size = frame.size.copy(),
        )
      )

      return ApplicationModel(pixelCrowd, circleCrowd)
    }
  }

  public fun update(): Boolean {
    var count = 0
    var chance = this.spawnChallenge
    val radius = this.spawnRadius

    while (chance > 0) {
      val center = this.pixelCrowd.random().point

      val success = this.circleCrowd.trySpawn(
        radius = radius,
        center = center,
      )

      if (success) {
        count += 1
      }
      if (count >= this.spawnSpeed) {
        break
      }

      chance -= 1
    }

    if (chance == 0) {
      return false
    }

    this.circleCrowd.update()

    // I don't know why, but this operation is very very slow.
    //
    // this.pixelCrowd.removeIf {
    //   this.circleCrowd.includes(it.point)
    // }
    return true
  }
}
