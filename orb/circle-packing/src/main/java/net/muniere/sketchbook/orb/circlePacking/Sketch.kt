package net.muniere.sketchbook.orb.circlePacking

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.Sketch
import net.muniere.sketchbook.lib.processing.background

public final class Sketch(size: Size2D) : Sketch(size) {
  private object Params {
    internal val CANVAS_COLOR = Colors.parse("#222222")
    internal val STROKE_COLOR = Colors.parse("#FFFFFF")

    internal const val SPAWN_SPEED = 10
    internal const val SPAWN_RADIUS = 5.0F
    internal const val SHAPE_ALPHA = 0.05F
    internal const val CHANCE_LIMIT = 1000
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g)
  )

  override fun doSetup() {
    super.doSetup()

    val image = this.loadImage("shape.png").also {
      it.loadPixels()
    }

    val frame = Rect2D(
      origin = Point2D.zero(),
      size = this.size.copy(),
    )

    this.model = ApplicationModel.create(
      frame = frame,
      image = image,
      predicate = { pixel ->
        this.brightness(pixel.value) > 1
      }
    ).also {
      it.spawnChance = Params.CHANCE_LIMIT
      it.spawnSpeed = Params.SPAWN_SPEED
      it.spawnRadius = Params.SPAWN_RADIUS
    }

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
    val success = this.model.update()
    if (!success) {
      this.noLoop()
    }
  }
}
