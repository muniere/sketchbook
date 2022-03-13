package net.muniere.sketchbook.orb.imageDithering

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.graphics.image
import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal class ImageMachineWidget(graphics: PGraphics) : ModelWidget<ImageMachineModel>(graphics) {
  public var frame: Rect2D = Rect2D(
    origin = Point2D.zero(),
    size = Size2D.zero(),
  )

  override fun doDraw(model: ImageMachineModel) {
    this.g.image(model.image, this.frame)
  }
}
