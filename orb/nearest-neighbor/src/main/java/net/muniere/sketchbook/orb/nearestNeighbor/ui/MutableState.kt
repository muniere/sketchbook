package net.muniere.sketchbook.orb.nearestNeighbor.ui

import kotlinx.coroutines.flow.MutableStateFlow

internal fun <T> MutableStateFlow<T>.update(transform: (T) -> T) {
  this.value = transform(this.value)
}
