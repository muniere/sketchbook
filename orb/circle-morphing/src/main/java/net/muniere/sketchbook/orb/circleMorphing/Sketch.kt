package net.muniere.sketchbook.orb.circleMorphing

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.Sketch
import net.muniere.sketchbook.lib.processing.background

public final class Sketch(size: Size2D) : Sketch(size) {

  private object Params {
    internal val CANVAS_COLOR = Colors.parse("#CCCCCC")
    internal val STROKE_COLOR = Colors.parse("#000000")

    internal const val RESOLUTION = 360 / 2
    internal const val SRC_RADIUS = 150.0F
    internal const val SRC_VERTEX_N = 0
    internal const val DST_RADIUS = 80.0F
    internal const val DST_VERTEX_N = 5
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g).also {
      it.textColor = Params.STROKE_COLOR
    }
  )

  override fun doSetup() {
    val src = when (Params.SRC_VERTEX_N >= 3) {
      true -> PathModels.polygon(
        n = Params.SRC_VERTEX_N,
        radius = Params.SRC_RADIUS,
        resolution = Params.RESOLUTION,
      )
      false -> PathModels.circle(
        radius = Params.SRC_RADIUS,
        resolution = Params.RESOLUTION,
      )
    }

    val dst = when (Params.DST_VERTEX_N >= 3) {
      true -> PathModels.polygon(
        n = Params.DST_VERTEX_N,
        radius = Params.DST_RADIUS,
        resolution = Params.RESOLUTION,
      )
      false -> PathModels.circle(
        radius = Params.DST_RADIUS,
        resolution = Params.RESOLUTION,
      )
    }

    this.model = ApplicationModel(
      morphing = InterpolationMorphingModel(
        src = src,
        dst = dst,
      )
    )

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
      it.color = Params.STROKE_COLOR
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.CANVAS_COLOR)
    this.g.translate(this.width.toFloat() / 2, this.height.toFloat() / 2)

    // widget
    this.widget.draw()

    // update
    this.model.update()
  }
}
