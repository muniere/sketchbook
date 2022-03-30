package net.muniere.sketchbook.orb.sortVision.quick

import net.muniere.sketchbook.lib.replace
import net.muniere.sketchbook.lib.swap
import net.muniere.sketchbook.orb.sortVision.SortMachineModel
import net.muniere.sketchbook.orb.sortVision.SortProcessState
import net.muniere.sketchbook.orb.sortVision.SortValueState

internal final class QuickSortMachineModel(values: List<Int>) : SortMachineModel() {

  override val values: List<Int>
    get() = this._values

  private val _values: MutableList<Int> = values.toMutableList()

  override val cursorIndex: Int
    get() = this._cursor

  private var _cursor: Int = -1

  public val pivotIndex: Int
    get() = this._values.indexOf(this._pivotValue)

  private var _pivotValue: Int = -1

  public val lowIndex: Int
    get() = this._lowIndex

  private var _lowIndex: Int = -1

  public val highIndex: Int
    get() = this._highIndex

  private var _highIndex: Int = -1

  private var _direction: Int = 0

  private val _sections = mutableListOf(IntRange(0, values.lastIndex))
  private var _sectionIndex: Int = 0

  init {
    this.prepareForSection()
  }

  private val currentSection: IntRange?
    get() = this._sections.getOrNull(this._sectionIndex)

  override val processState: SortProcessState
    get() = when (this._sectionIndex > this._sections.lastIndex) {
      true -> SortProcessState.FINISHED
      false -> SortProcessState.RUNNING
    }

  override fun valueState(index: Int): SortValueState {
    val section = this.currentSection ?: run {
      return SortValueState.FIXED
    }

    return when (index < section.first) {
      true -> SortValueState.FIXED
      false -> SortValueState.UNFIXED
    }
  }

  override fun cycle() {
    val section = this.currentSection ?: run {
      return
    }

    if (section.count() <= 1) {
      this._sectionIndex += 1
      this.prepareForSection()
      return
    }

    when (this._direction) {
      1 -> {
        if (this._cursor <= section.last && this._values[this._cursor] < this._pivotValue) {
          this._cursor += 1
          return
        }

        if (this._cursor <= section.last) {
          this._lowIndex = this._cursor
        } else {
          // guard block for the worst pivot with the maximum value
          this._lowIndex = section.last
          this._values.swap(section.first, this.pivotIndex)
        }

        this._cursor = this._highIndex
        this._direction = -1
      }
      -1 -> {
        if (this._cursor >= section.first && this._values[this._cursor] >= this._pivotValue) {
          this._cursor -= 1
          return
        }

        if (this._cursor >= section.first) {
          this._highIndex = this._cursor
        } else {
          // guard block for the worst pivot with the minimum value
          this._highIndex = section.first
          this._values.swap(section.first, this.pivotIndex)
        }

        this._cursor = this._lowIndex
        this._direction = 1

        if (this._lowIndex < this._highIndex) {
          this._values.swap(this._lowIndex, this._highIndex)
          this._lowIndex += 1
          this._highIndex -= 1
          this._cursor = this._lowIndex
        } else {
          val newSections = when (this._lowIndex == section.first) {
            true -> listOf(
              IntRange(section.first, section.first),
              IntRange(section.first + 1, section.last),
            )
            false -> listOf(
              IntRange(section.first, this._lowIndex - 1),
              IntRange(this._lowIndex, section.last),
            )
          }
          this._sections.replace(this._sectionIndex, newSections)
          this.prepareForSection()
        }
      }
    }
  }

  private fun prepareForSection() {
    val section = this.currentSection ?: run {
      return
    }

    val pivotIndex = (section.first + section.last) / 2
    this._pivotValue = this._values[pivotIndex]
    this._cursor = section.first
    this._direction = 1
    this._lowIndex = section.first
    this._highIndex = section.last
  }
}
