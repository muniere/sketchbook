package net.muniere.sketchbook.orb.purpleRain

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Point3D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.map
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.Sketch
import net.muniere.sketchbook.lib.processing.background

public final class Sketch(size: Size2D) : Sketch(size) {

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  private object Params {
    internal val CANVAS_COLOR = Color.valueOf(0xE6E6FA)
    internal val DROP_COLOR = Color.valueOf(0x8A2BE2)
    internal const val DROP_COUNT = 200
  }

  override fun configure() = listOf(
    MetricsPlugin(this.g).also {
      it.textColor = Params.DROP_COLOR
    }
  )

  override fun doSetup() {
    this.model = ApplicationModel(
      frame = Rect2D(
        origin = Point2D.zero(),
        size = this.size,
      ),
      drops = List(Params.DROP_COUNT) {
        val origin = Point3D(
          x = random(this.size.width),
          y = random(-500.0F),
          z = random(20.0F),
        )
        val length = origin.z.map(
          domain = 0.0F..20.0F,
          target = 10.0F..50.0F,
        )
        return@List DropModel(
          origin = origin,
          length = length,
        )
      }
    ).also {
      it.color = Params.DROP_COLOR
    }

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
    }
  }

  override fun doDraw() {
    this.g.background(Params.CANVAS_COLOR)

    this.widget.draw()

    this.model.update()
  }
}
