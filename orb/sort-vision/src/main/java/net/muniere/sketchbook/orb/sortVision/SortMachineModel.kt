package net.muniere.sketchbook.orb.sortVision

internal abstract class SortMachineModel {
  abstract val values: List<Int>
  abstract val cursorIndex: Int
  abstract val processState: SortProcessState
  abstract fun valueState(index: Int): SortValueState
  abstract fun cycle(): Unit
}
