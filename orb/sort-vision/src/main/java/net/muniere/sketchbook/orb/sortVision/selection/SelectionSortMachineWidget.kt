package net.muniere.sketchbook.orb.sortVision.selection

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.orb.sortVision.SortMachineWidget
import net.muniere.sketchbook.orb.sortVision.SortProcessState
import net.muniere.sketchbook.orb.sortVision.SortValueState
import processing.core.PGraphics

internal class SelectionSortMachineWidget(graphics: PGraphics) : SortMachineWidget<SelectionSortMachineModel>(graphics) {

  public var targetColor: Color = Colors.WHITE

  override fun colorize(model: SelectionSortMachineModel, index: Int): Color {
    if (model.processState == SortProcessState.FINISHED) {
      return this.answerColor
    }
    if (model.valueState(index) == SortValueState.FIXED) {
      return this.answerColor
    }
    if (index == model.cursorIndex) {
      return this.cursorColor
    }
    if (index == model.targetIndex) {
      return this.targetColor
    }
    return this.defaultColor
  }
}
