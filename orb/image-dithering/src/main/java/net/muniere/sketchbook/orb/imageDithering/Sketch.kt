package net.muniere.sketchbook.orb.imageDithering

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.Sketch

public final class Sketch : Sketch() {

  private object Params {
    internal object Image {
      internal const val FILENAME = "image.jpg"
    }

    internal object Dither {
      internal const val SCALE = 2
      internal const val SPEED = 1
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun doSetup() {
    super.doSetup()

    val image = this.loadImage(Params.Image.FILENAME)

    val scaling = minOf(
      this.width.toFloat() / image.width.toFloat(),
      this.height.toFloat() / image.height.toFloat(),
    )

    val machine = ImageMachineModel(
      image = image,
      scale = Params.Dither.SCALE,
    ).also {
      it.image.loadPixels()
      it.speed = it.image.width * Params.Dither.SPEED
    }

    this.model = ApplicationModel(
      machine = machine,
    )

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
      it.frame = Rect2D(
        origin = Point2D.zero(),
        size = Size2D(
          width = image.width * scaling,
          height = image.height * scaling,
        )
      )
    }
  }

  override fun doDraw() {
    this.widget.draw()

    if (!this.model.hasNext) {
      this.noLoop()
      return
    }

    this.model.update()
  }
}
