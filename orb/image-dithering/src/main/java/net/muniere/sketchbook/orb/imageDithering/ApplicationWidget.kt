package net.muniere.sketchbook.orb.imageDithering

import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  private val image = ImageMachineWidget(graphics)

  public var frame: Rect2D
    get() = this.image.frame
    set(value) = this.image::frame.set(value)

  override fun doDraw(model: ApplicationModel) {
    this.image.model = model.machine
    this.image.draw()
  }
}
