package net.muniere.sketchbook.orb.sortVision.quick

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.orb.sortVision.SortMachineWidget
import net.muniere.sketchbook.orb.sortVision.SortProcessState
import net.muniere.sketchbook.orb.sortVision.SortValueState
import processing.core.PGraphics

internal final class QuickSortMachineWidget(graphics: PGraphics) : SortMachineWidget<QuickSortMachineModel>(graphics) {

  public var pivotColor: Color = Colors.WHITE
  public var lowColor: Color = Colors.WHITE
  public var highColor: Color = Colors.WHITE

  override fun colorize(model: QuickSortMachineModel, index: Int): Color {
    if (model.processState == SortProcessState.FINISHED) {
      return this.answerColor
    }
    if (model.valueState(index) == SortValueState.FIXED) {
      return this.answerColor
    }
    if (index == model.cursorIndex) {
      return this.cursorColor
    }
    if (index == model.pivotIndex) {
      return this.pivotColor
    }
    if (index == model.lowIndex) {
      return this.lowColor
    }
    if (index == model.highIndex) {
      return this.highColor
    }
    return this.defaultColor
  }
}

