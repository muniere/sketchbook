package net.muniere.sketchbook.orb.sortVision.insertion

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.orb.sortVision.SortMachineWidget
import net.muniere.sketchbook.orb.sortVision.SortProcessState
import net.muniere.sketchbook.orb.sortVision.SortValueState
import processing.core.PGraphics

internal class InsertionSortMachineWidget(graphics: PGraphics) : SortMachineWidget<InsertionSortMachineModel>(graphics) {

  public var pivotColor: Color = Colors.WHITE

  override fun colorize(model: InsertionSortMachineModel, index: Int): Color {
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
    return this.defaultColor
  }
}
