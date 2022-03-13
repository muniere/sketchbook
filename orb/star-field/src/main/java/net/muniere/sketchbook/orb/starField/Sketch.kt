package net.muniere.sketchbook.orb.starField

import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Point3D
import net.muniere.sketchbook.lib.map
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.Sketch
import net.muniere.sketchbook.lib.processing.background

public final class Sketch : Sketch() {

  private object Params {
    internal object Canvas {
      internal val COLOR = Colors.parse("#111111")
    }

    internal object Star {
      internal const val RADIUS = 5.0F
      internal const val COUNT = 1000
      internal const val SPEED_MIN = 10.0F
      internal const val SPEED_MAX = 100.0F
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g)
  )

  override fun doSetup() {
    val xs = FloatRange(-this.width / 2.0F, this.width / 2.0F)
    val ys = FloatRange(-this.height / 2.0F, this.height / 2.0F)
    val zs = FloatRange(0.0F, this.width.toFloat())

    val stars = List(Params.Star.COUNT) {
      StarModel(
        radius = Params.Star.RADIUS,
        center = Point3D(
          x = xs.random(),
          y = ys.random(),
          z = zs.random()
        )
      )
    }

    this.model = ApplicationModel(
      stars = stars
    )

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
    }
  }

  override fun doDraw() {
    this.g.background(Params.Canvas.COLOR)
    this.widget.draw()
    this.model.update()
  }

  override fun touchMoved() {
    val canvasOrigin = Point2D.zero()

    val canvasCenter = Point2D(
      x = this.width / 2.0F,
      y = this.height / 2.0F,
    )

    val mousePoint = Point2D(
      x = this.mouseX.toFloat(),
      y = this.mouseY.toFloat(),
    )

    this.model.starField.setSpeed(
      Point2D.dist(mousePoint, canvasCenter).map(
        domain = FloatRange(0.0F, Point2D.dist(canvasOrigin, canvasCenter)),
        target = FloatRange(Params.Star.SPEED_MIN, Params.Star.SPEED_MAX),
      )
    )
  }
}
