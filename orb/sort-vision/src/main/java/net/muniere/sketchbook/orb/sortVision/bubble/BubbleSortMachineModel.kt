package net.muniere.sketchbook.orb.sortVision.bubble

import net.muniere.sketchbook.lib.swap
import net.muniere.sketchbook.orb.sortVision.SortMachineModel
import net.muniere.sketchbook.orb.sortVision.SortProcessState
import net.muniere.sketchbook.orb.sortVision.SortValueState

internal final class BubbleSortMachineModel(values: List<Int>) : SortMachineModel() {

  override val values: List<Int>
    get() = this._values

  private val _values: MutableList<Int> = values.toMutableList()

  override val cursorIndex: Int
    get() = this._cursorIndex

  private var _cursorIndex: Int = 0

  private var _stopIndex: Int = values.lastIndex

  override val processState: SortProcessState
    get() = when (this._stopIndex <= 0) {
      true -> SortProcessState.FINISHED
      false -> SortProcessState.RUNNING
    }

  override fun valueState(index: Int): SortValueState {
    return when (index > this._stopIndex) {
      true -> SortValueState.FIXED
      false -> SortValueState.UNFIXED
    }
  }

  override fun cycle() {
    if (this.processState == SortProcessState.FINISHED) {
      return
    }

    if (this.values[this._cursorIndex] > this.values[this._cursorIndex + 1]) {
      this._values.swap(this._cursorIndex, this._cursorIndex + 1)
    }

    this._cursorIndex += 1

    if (this._cursorIndex >= this._stopIndex) {
      this._cursorIndex = 0
      this._stopIndex -= 1
    }
  }
}
