package net.muniere.sketchbook.lib

public fun <T> List<T>.collect(indices: List<Int>): List<T> {
  return indices.map(this::get)
}

public fun <T> MutableList<T>.swap(i: Int, j: Int) {
  val tmp = this[i]
  this[i] = this[j]
  this[j] = tmp
}

public fun <T> MutableList<T>.replace(index: Int, elements: Collection<T>) {
  this.removeAt(index)
  this.addAll(index, elements)
}

public fun <T> MutableList<T>.reverse(fromIndex: Int, toIndex: Int) {
  this.subList(fromIndex, toIndex).reversed().forEachIndexed { index, value ->
    this[fromIndex + index] = value
  }
}

public fun <T> MutableList<T>.reverseBefore(index: Int) {
  this.reverse(fromIndex = 0, toIndex = index)
}

public fun <T> MutableList<T>.reverseAfter(index: Int) {
  this.reverse(fromIndex = index, toIndex = this.size)
}

public fun <T : Comparable<T>> Collection<T>.permutation(): Sequence<List<T>> = sequence {
  val seq = this@permutation.toMutableList()

  yield(seq.toList())

  while (true) {
    val x = seq.zipWithNext().indexOfLast { it.first < it.second }
    if (x < 0) {
      break
    }

    val v = seq[x]
    val y = seq.withIndex().indexOfLast { it.index > x && it.value > v }
    if (y < 0) {
      break
    }

    seq.swap(x, y)
    seq.reverseAfter(x + 1)

    yield(seq.toList())
  }
}
