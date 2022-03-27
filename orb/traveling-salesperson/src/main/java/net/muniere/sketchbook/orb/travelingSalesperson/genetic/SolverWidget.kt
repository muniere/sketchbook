package net.muniere.sketchbook.orb.travelingSalesperson.genetic

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.orb.travelingSalesperson.PathWidget
import net.muniere.sketchbook.orb.travelingSalesperson.ProgressWidget
import processing.core.PGraphics

internal final class SolverWidget(graphics: PGraphics) : ModelWidget<SolverModel>(graphics) {

  public var pathColor: Color = Colors.WHITE
  public var pathWeight: Float = 1.0F

  public var candidateColor: Color = Colors.WHITE
  public var candidateWeight: Float = 1.0F

  public var answerColor: Color = Colors.WHITE
  public var answerWeight: Float = 1.0F

  public var labelOrigin: Point2D
    get() = this.label.origin
    set(value) = this.label::origin.set(value)

  public var labelColor: Color
    get() = this.label.textColor
    set(value) = this.label::textColor.set(value)

  public var labelSize: Float
    get() = this.label.textSize
    set(value) = this.label::textSize.set(value)

  private val path = PathWidget(graphics)
  private val label = ProgressWidget(graphics)

  override fun doDraw(model: SolverModel) {
    if (model.paths.isNotEmpty() && model.hasNext()) {
      model.paths.forEach { path ->
        this.path.model = path;
        this.path.color = this.pathColor
        this.path.weight = this.pathWeight
        this.path.draw()
      }
    }

    if (model.answer != null) {
      this.path.model = model.answer

      if (model.hasNext()) {
        this.path.color = this.candidateColor
        this.path.weight = this.candidateWeight
      } else {
        this.path.color = this.answerColor
        this.path.weight = this.answerWeight
      }

      this.path.draw()
    }

    this.label.model = model.progress
    this.label.draw()
  }
}
