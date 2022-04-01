package net.muniere.sketchbook.lib.math

import kotlin.math.sqrt

public fun factorial(n: Int): Int {
  require(n >= 0)
  if (n == 0) {
    return 1
  }
  if (n == 1) {
    return 1
  }

  var result = 1
  for (x in n downTo 1) {
    result *= x
  }
  return result
}

public fun isPrime(n: Int): Boolean {
  if (n == 1) {
    return false
  }
  if (n == 2) {
    return true
  }
  val m = sqrt(n.toDouble()).toInt()
  return IntRange(2, m).any { n % it == 0 }
}
