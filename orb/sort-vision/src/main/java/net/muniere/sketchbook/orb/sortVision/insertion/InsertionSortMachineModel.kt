package net.muniere.sketchbook.orb.sortVision.insertion

import net.muniere.sketchbook.orb.sortVision.SortMachineModel
import net.muniere.sketchbook.orb.sortVision.SortProcessState
import net.muniere.sketchbook.orb.sortVision.SortValueState

internal final class InsertionSortMachineModel(values: List<Int>) : SortMachineModel() {

  override val values: List<Int>
    get() = this._values

  private val _values: MutableList<Int> = values.toMutableList()

  override val cursorIndex: Int
    get() = this._cursorIndex

  private var _cursorIndex: Int = 1

  public val pivotIndex: Int
    get() = this._pivotIndex

  private var _pivotIndex: Int = 1

  public val pivotValue: Int
    get() = this._pivotValue

  private var _pivotValue: Int = -1

  override val processState: SortProcessState
    get() = when (this._pivotIndex > this._values.lastIndex) {
      true -> SortProcessState.FINISHED
      false -> SortProcessState.RUNNING
    }

  override fun valueState(index: Int): SortValueState {
    return when (this.processState == SortProcessState.FINISHED) {
      true -> SortValueState.FIXED
      false -> SortValueState.UNFIXED
    }
  }

  override fun cycle() {
    if (this.processState == SortProcessState.FINISHED) {
      return
    }

    if (this._cursorIndex <= 0) {
      this._values[this._cursorIndex] = this._pivotValue
      this._pivotIndex += 1
      this._cursorIndex = this._pivotIndex
      return
    }

    if (this._cursorIndex == this._pivotIndex) {
      this._pivotValue = this._values[this._pivotIndex]
    }

    if (this._cursorIndex == this._pivotIndex && this._values[this._pivotIndex - 1] <= this._values[this._pivotIndex]) {
      this._pivotIndex += 1
      this._cursorIndex = this._pivotIndex
      return
    }

    if (this._values[this._cursorIndex - 1] <= this._pivotValue) {
      this._values[this._cursorIndex] = this._pivotValue
      this._pivotIndex += 1
      this._cursorIndex = this._pivotIndex
      return
    }

    this._values[this._cursorIndex] = this._values[this._cursorIndex - 1]
    this._cursorIndex -= 1
  }
}
