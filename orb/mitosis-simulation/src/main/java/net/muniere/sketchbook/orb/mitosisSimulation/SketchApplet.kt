package net.muniere.sketchbook.orb.mitosisSimulation

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.background

internal final class SketchApplet : SApplet() {
  private object Params {
    internal val CANVAS_COLOR = Colors.parse("#333333")
    internal val CELL_RADIUS = 50.0F
    internal val CELL_GROWTH = 1.01F
    internal val CELL_COUNT = 10
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun doSetup() {
    val frame = Rect2D(
      origin = Point2D.zero(),
      size = this.size
    )

    this.model = ApplicationModel(
      frame = frame,
      cells = List(Params.CELL_COUNT) {
        CellModel(
          center = frame.random(),
          radius = Params.CELL_RADIUS,
          growth = Params.CELL_GROWTH,
          limit = Params.CELL_RADIUS,
        ).also {
          it.fillColor = Colors.random(alpha = 0.5F)
        }
      }
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

  override fun touchEnded() {
    val point = Point2D(
      x = this.mouseX.toFloat(),
      y = this.mouseY.toFloat(),
    )

    val index = this.model.findIndex(point)
    if (index >= 0) {
      this.model.split(index)
    }
  }
}
