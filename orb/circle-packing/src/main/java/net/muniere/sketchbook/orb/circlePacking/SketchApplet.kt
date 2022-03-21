package net.muniere.sketchbook.orb.circlePacking

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.background

internal final class SketchApplet : SApplet() {

  private object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#222222")
    }

    internal object Shape {
      internal val STROKE_COLOR = Colors.parse("#FFFFFF")
    }

    internal object Spawn {
      internal const val SPEED = 10
      internal const val RADIUS = 5.0F
      internal const val CHALLENGE = 1000
    }
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
      it.spawnChallenge = Params.Spawn.CHALLENGE
      it.spawnSpeed = Params.Spawn.SPEED
      it.spawnRadius = Params.Spawn.RADIUS
    }

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.Canvas.BACKGROUND_COLOR)

    // widget
    this.widget.draw()

    // update
    val success = this.model.update()
    if (!success) {
      this.noLoop()
    }
  }
}
