package net.muniere.sketchbook.orb.starField

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Point3D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.map
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.Sketch
import net.muniere.sketchbook.lib.processing.background

public final class Sketch(size: Size2D) : Sketch(size) {

  private object Params {
    internal val CANVAS_COLOR = Color.valueOf(0x111111)
    internal const val STAR_RADIUS = 5.0F
    internal const val STAR_COUNT = 1000
    internal const val STAR_SPEED_MIN = 10.0F
    internal const val STAR_SPEED_MAX = 100.0F
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g)
  )

  override fun doSetup() {
    this.model = ApplicationModel(
      stars = List(Params.STAR_COUNT) {
        StarModel(
          radius = Params.STAR_RADIUS,
          center = Point3D(
            x = random(-this.width / 2.0F, this.width / 2.0F),
            y = random(-this.height / 2.0F, this.height / 2.0F),
            z = random(this.width.toFloat())
          )
        )
      }
    )

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
    }
  }

  override fun doDraw() {
    this.g.background(Params.CANVAS_COLOR)
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
        domain = 0.0F..Point2D.dist(canvasOrigin, canvasCenter),
        target = Params.STAR_SPEED_MIN..Params.STAR_SPEED_MAX,
      )
    )
  }
}
