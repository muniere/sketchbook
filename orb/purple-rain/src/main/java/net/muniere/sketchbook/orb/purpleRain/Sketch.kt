package net.muniere.sketchbook.orb.purpleRain

import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.drawing.Colors
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
    internal object Canvas {
      internal val COLOR = Colors.parse("#E6E6FA")
    }

    internal object Drop {
      internal val COLOR = Colors.parse("#8A2BE2")
      internal const val COUNT = 200
    }
  }

  override fun configure() = listOf(
    MetricsPlugin(this.g).also {
      it.textColor = Params.Drop.COLOR
    }
  )

  override fun doSetup() {
    val frame = Rect2D(
      origin = Point2D.zero(),
      size = this.size,
    )

    val drops = List(Params.Drop.COUNT) {
      val origin = Point3D(
        x = random(this.size.width),
        y = random(-500.0F),
        z = random(20.0F),
      )
      val length = origin.z.map(
        domain = FloatRange(0.0F, 20.0F),
        target = FloatRange(10.0F, 50.0F),
      )
      return@List DropModel(
        origin = origin,
        length = length,
      )
    }

    this.model = ApplicationModel(
      frame = frame,
      drops = drops,
    ).also {
      it.color = Params.Drop.COLOR
    }

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
    }
  }

  override fun doDraw() {
    this.g.background(Params.Canvas.COLOR)

    this.widget.draw()

    this.model.update()
  }
}
