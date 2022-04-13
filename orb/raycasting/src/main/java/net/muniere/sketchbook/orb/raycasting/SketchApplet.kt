package net.muniere.sketchbook.orb.raycasting

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.background

public final class SketchApplet : SApplet() {

  private object Params {
    internal val CANVAS_COLOR = Colors.parse("#222222")
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun doSetup() {
    this.model = ApplicationModel(
      frame = Rect2D(
        origin = Point2D.zero(),
        size = this.size,
      ),
      walls = listOf(
        WallModel(
          Point2D(x = 0.0F, y = 0.0F),
          Point2D(x = this.width.toFloat(), y = 0.0F),
        ),
        WallModel(
          Point2D(x = this.width.toFloat(), y = 0.0F),
          Point2D(x = this.width.toFloat(), y = this.height.toFloat()),
        ),
        WallModel(
          Point2D(x = this.width.toFloat(), y = this.height.toFloat()),
          Point2D(x = 0.0F, y = this.height.toFloat()),
        ),
        WallModel(
          Point2D(x = 0.0F, y = this.height.toFloat()),
          Point2D(x = 0.0F, y = 0.0F),
        ),
        WallModel(
          Point2D(x = 500.0F, y = 300.0F),
          Point2D(x = 500.0F, y = 800.0F),
        ),
        WallModel(
          Point2D(x = 100.0F, y = 100.0F),
          Point2D(x = 400.0F, y = 1200.0F),
        ),
        WallModel(
          Point2D(x = 200.0F, y = 900.0F),
          Point2D(x = 700.0F, y = 800.0F),
        ),
        WallModel(
          Point2D(x = 800.0F, y = 200.0F),
          Point2D(x = 100.0F, y = 900.0F),
        ),
      ),
      particle = ParticleModel(
        size = Size2D.square(20.0F),
        center = Point2D(
          x = this.width.toFloat() / 2,
          y = this.height.toFloat() / 2,
        ),
        rayResolution = 180,
      ),
    )

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.CANVAS_COLOR)

    // widget
    this.widget.draw()

    // update
    this.model.update()
  }

  override fun touchStarted() {
    this.model.warpTo(
      Point2D(
        x = this.mouseX.toFloat(),
        y = this.mouseY.toFloat(),
      )
    )
  }

  override fun touchMoved() {
    this.model.warpTo(
      Point2D(
        x = this.mouseX.toFloat(),
        y = this.mouseY.toFloat(),
      )
    )
  }
}
