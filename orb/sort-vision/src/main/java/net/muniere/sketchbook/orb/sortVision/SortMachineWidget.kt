package net.muniere.sketchbook.orb.sortVision

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import processing.core.PGraphics

internal abstract class SortMachineWidget<Model : SortMachineModel>(graphics: PGraphics) : ModelWidget<Model>(graphics) {
  public var frame = Rect2D(
    origin = Point2D.zero(),
    size = Size2D.zero(),
  )
  public var defaultColor: Color = Colors.WHITE
  public var cursorColor: Color = Colors.WHITE
  public var answerColor: Color = Colors.WHITE

  override fun doDraw(model: Model) {
    val blockSize = Size2D(
      width = this.frame.width / model.values.size,
      height = this.frame.height / model.values.size,
    )

    this.scope { graphics ->
      model.values.forEachIndexed { index, value ->
        val w = blockSize.width
        val h = value * blockSize.height
        val x = index * blockSize.width
        val y = this.frame.height - h
        val c = this.colorize(model, index)
        graphics.fill(c)
        graphics.rect(x, y, w, h)
      }
    }
  }

  protected open fun colorize(model: Model, index: Int): Color {
    if (model.processState == SortProcessState.FINISHED) {
      return this.answerColor
    }
    if (model.valueState(index) == SortValueState.FIXED) {
      return this.answerColor
    }
    if (index == model.cursorIndex) {
      return this.cursorColor
    }
    return this.defaultColor
  }
}

