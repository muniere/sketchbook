package net.muniere.sketchbook.orb.quadtree

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.physics.Force2D
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.SketchApplet
import net.muniere.sketchbook.lib.processing.SketchPlugin
import net.muniere.sketchbook.lib.processing.background
import processing.core.PConstants
import kotlin.random.Random

internal final class SketchApplet : SketchApplet() {

  private object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#FFFFFF")
    }

    internal object Metrics {
      internal val TEXT_COLOR = Colors.parse("#222222")
    }

    internal object Division {
      internal val STROKE_COLOR = Colors.parse("#888888")
      internal const val CAPACITY = 2
    }

    internal object Material {
      internal val STROKE_COLOR = Colors.parse("#888888")
      internal const val COUNT = 100
      internal const val RADIUS = 10.0F
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf<SketchPlugin>(
    MetricsPlugin(this.g).also {
      it.textColor = Params.Metrics.TEXT_COLOR
    }
  )

  override fun settings() {
    minOf(this.displayWidth, this.displayHeight).let {
      this.size(it, it, PConstants.P2D)
    }
  }

  override fun doSetup() {
    val boundary = Rect2D(
      origin = Point2D.zero(),
      size = Size2D(
        width = this.width.toFloat(),
        height = this.height.toFloat(),
      )
    )

    val tree = TreeModel(
      root = DivisionModel(
        boundary = boundary,
        capacity = Params.Division.CAPACITY,
      )
    )

    val materials = List(Params.Material.COUNT) {
      MaterialModel(
        radius = Params.Material.RADIUS,
        center = Point2D(
          x = this.size.width * Random.nextFloat(),
          y = this.size.height * Random.nextFloat(),
        ),
      ).also {
        val force = Force2D(
          x = Random.nextFloat(),
          y = Random.nextFloat(),
        )
        it.fillColor = Params.Material.STROKE_COLOR
        it.strokeColor = null
        it.apply(force)
      }
    }

    this.model = ApplicationModel(tree).also {
      it.addAll(materials)
    }

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
      it.fillColor = null
      it.strokeColor = Params.Division.STROKE_COLOR
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.Canvas.BACKGROUND_COLOR)

    // widget
    this.widget.draw()

    // update
    this.model.update()
  }
}

