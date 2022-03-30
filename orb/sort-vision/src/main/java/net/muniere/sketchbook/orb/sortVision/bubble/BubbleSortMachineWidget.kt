package net.muniere.sketchbook.orb.sortVision.bubble

import android.graphics.Color
import net.muniere.sketchbook.orb.sortVision.SortMachineWidget
import net.muniere.sketchbook.orb.sortVision.SortProcessState
import net.muniere.sketchbook.orb.sortVision.SortValueState
import processing.core.PGraphics

internal class BubbleSortMachineWidget(graphics: PGraphics) : SortMachineWidget<BubbleSortMachineModel>(graphics) {

  override fun colorize(model: BubbleSortMachineModel, index: Int): Color {
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

