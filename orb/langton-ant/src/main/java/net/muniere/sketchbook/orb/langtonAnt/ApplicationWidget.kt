package net.muniere.sketchbook.orb.langtonAnt

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics
import kotlin.math.min

internal final class ApplicationWidget(graphics: PGraphics) : ModelWidget<ApplicationModel>(graphics) {
  public var frame = Rect2D(
    origin = Point2D.zero(),
    size = Size2D.zero(),
  )

  public var fillColor: Color
    get() = this.grid.fillColor
    set(value) = this.grid::fillColor.set(value)

  public var lineColor: Color
    get() = this.grid.lineColor
    set(value) = this.grid::lineColor.set(value)

  public var antColor: Color
    get() = this.ant.color
    set(value) = this.ant::color.set(value)

  public var textColor: Color
    get() = this.step.color
    set(value) = this.step::color.set(value)

  private val grid = GridWidget(graphics)
  private val ant = AntWidget(graphics)
  private val step = StepWidget(graphics)

  override fun doDraw(model: ApplicationModel) {
    this.grid.model = model.grid
    this.grid.frame = this.gridFrame()
    this.grid.draw()

    model.ants.forEach { ant ->
      this.ant.model = ant
      this.ant.frame = this.antFrame(ant, model.grid)
      this.ant.draw()
    }

    this.step.model = model.step
    this.step.origin = this.stepOrigin()
    this.step.draw()
  }

  private fun gridFrame(): Rect2D {
    return Rect2D(
      origin = this.frame.origin.copy(),
      size = Size2D.square(
        size = min(this.frame.width, this.frame.height),
      )
    )
  }

  private fun antFrame(ant: AntModel, grid: GridModel): Rect2D {
    val cellSize = Size2D(
      width = this.frame.width / grid.dimen.width,
      height = this.frame.height / grid.dimen.height,
    )

    val cellOrigin = Point2D(
      x = cellSize.width * ant.spot.column,
      y = cellSize.width * ant.spot.row,
    )

    val antSize = cellSize * 0.8F

    val antOrigin = cellOrigin.movingBy(
      x = (cellSize.width - antSize.width) / 2,
      y = (cellSize.height - antSize.height) / 2,
    )

    return Rect2D(
      origin = antOrigin,
      size = antSize,
    )
  }

  private fun stepOrigin(): Point2D {
    return Point2D(
      x = this.frame.width - 10,
      y = min(this.frame.width, this.frame.height),
    )
  }
}
