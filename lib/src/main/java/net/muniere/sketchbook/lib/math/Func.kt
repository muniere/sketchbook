package net.muniere.sketchbook.lib.math

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
