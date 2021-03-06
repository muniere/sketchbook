package net.muniere.sketchbook.lib

public fun <A, B> product(a: Iterable<A>, b: Iterable<B>): List<Pair<A, B>> {
  val nested = a.map { va ->
    b.map { vb -> va to vb }
  }

  return nested.flatten()
}

public fun <A, B, C> product(a: Iterable<A>, b: Iterable<B>, c: Iterable<C>): List<Triple<A, B, C>> {
  val nested = a.map { va ->
    b.map { vb ->
      c.map { vc -> Triple(va, vb, vc) }
    }
  }

  return nested.flatten().flatten()
}

public inline fun <T> Iterable<T>.sumOf(selector: (T) -> Float): Float {
  var sum: Float = 0.0F
  for (element in this) {
    sum += selector(element)
  }
  return sum
}
