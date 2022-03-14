package net.muniere.sketchbook.orb.fourierTransform.math

import net.muniere.sketchbook.lib.math.Complex

internal object FourierFormula {

  public fun apply(xs: List<Complex>): List<Complex> {
    val N = xs.size
    val t = -(2 * Math.PI) / N

    return List(N) { k ->
      xs.foldIndexed(Complex.zero()) { n, acc, x ->
        acc + x * Complex.unit(k * n * t)
      }
    }
  }
}
