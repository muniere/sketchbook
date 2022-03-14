package net.muniere.sketchbook.lib.math

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

public final data class Complex(
  public val re: Double = 0.0,
  public val im: Double = 0.0,
) {

  public companion object {
    public fun zero() = Complex(0.0, 0.0)

    public fun unit(theta: Double) = Complex(
      re = cos(theta),
      im = sin(theta),
    )
  }

  public fun norm(): Double {
    return sqrt(this.re * this.re + this.im * this.im);
  }

  public operator fun plus(other: Complex) = Complex(
    re = this.re + other.re,
    im = this.im + other.im,
  )

  public operator fun plus(other: Int) = Complex(
    re = this.re + other,
    im = this.im,
  )

  public operator fun plus(other: Long) = Complex(
    re = this.re + other,
    im = this.im,
  )

  public operator fun plus(other: Float) = Complex(
    re = this.re + other,
    im = this.im,
  )

  public operator fun plus(other: Double) = Complex(
    re = this.re + other,
    im = this.im,
  )

  public operator fun minus(other: Complex) = Complex(
    re = this.re - other.re,
    im = this.im - other.im,
  )

  public operator fun minus(other: Int) = Complex(
    re = this.re - other,
    im = this.im,
  )

  public operator fun minus(other: Long) = Complex(
    re = this.re - other,
    im = this.im,
  )

  public operator fun minus(other: Float) = Complex(
    re = this.re - other,
    im = this.im,
  )

  public operator fun minus(other: Double) = Complex(
    re = this.re - other,
    im = this.im,
  )

  public operator fun times(other: Complex) = Complex(
    re = this.re * other.re - this.im * other.im,
    im = this.re * other.im + this.im * other.re,
  )

  public operator fun times(other: Int) = Complex(
    re = this.re * other,
    im = this.im * other,
  )

  public operator fun times(other: Long) = Complex(
    re = this.re * other,
    im = this.im * other,
  )

  public operator fun times(other: Float) = Complex(
    re = this.re * other,
    im = this.im * other,
  )

  public operator fun times(other: Double) = Complex(
    re = this.re * other,
    im = this.im * other,
  )

  public operator fun div(other: Complex): Complex {
    val base = other.re * other.re + other.im * other.im

    return Complex(
      re = (this.re * other.re + this.im * other.im) / base,
      im = (this.im * other.re - this.re * other.im) / base,
    )
  }

  public operator fun div(other: Int) = Complex(
    re = this.re / other,
    im = this.im / other,
  )

  public operator fun div(other: Long) = Complex(
    re = this.re / other,
    im = this.im / other,
  )

  public operator fun div(other: Float) = Complex(
    re = this.re / other,
    im = this.im / other,
  )

  public operator fun div(other: Double) = Complex(
    re = this.re / other,
    im = this.im / other,
  )
}
