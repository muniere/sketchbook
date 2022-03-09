package net.muniere.sketchbook.lib.drawing

import net.muniere.sketchbook.lib.graphics.Point2D
import processing.core.PImage

public val PImage.pixel2Ds: List<Pixel2D>
  get() = this.pixels.mapIndexed { index, value ->
    Pixel2D(
      point = Point2D(
        x = (index % this.width).toFloat(),
        y = (index / this.width).toFloat(),
      ),
      value = value
    )
  }
