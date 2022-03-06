package net.muniere.sketchbook.lib.physics

import processing.core.PVector

public final class Acceleration2D(
  x: Float,
  y: Float,
) {

  public constructor(vector: PVector) : this(vector.x, vector.y)

  public companion object {
    public fun zero() = Acceleration2D(0.0F, 0.0F)
  }

  private val vector = PVector(x, y, 0.0F)

  public val x: Float
    get() = this.vector.x

  public val y: Float
    get() = this.vector.y

  public fun toVector(): PVector {
    return this.vector.copy()
  }

  public fun with(other: Acceleration2D): Acceleration2D {
    return Acceleration2D(other.vector)
  }

  public fun assign(other: Acceleration2D) {
    this.vector.set(other.vector)
  }

  public fun rotate(angle: Float): Acceleration2D {
    return Acceleration2D(this.vector.copy().rotate(angle))
  }

  public fun rotateAssign(angle: Float) {
    this.vector.rotate(angle)
  }

  public fun limit(magnitude: Float): Acceleration2D {
    return Acceleration2D(this.vector.copy().limit(magnitude))
  }

  public fun limitAssign(magnitude: Float) {
    this.vector.limit(magnitude)
  }

  public fun normalize(): Acceleration2D {
    return Acceleration2D(this.vector.copy().normalize())
  }

  public fun normalizeAssign() {
    this.vector.normalize()
  }

  public fun magnitude(): Float {
    return this.vector.mag()
  }

  public fun withMagnitude(magnitude: Float): Acceleration2D {
    return Acceleration2D(this.vector.copy().setMag(magnitude))
  }

  public fun setMagnitude(magnitude: Float) {
    this.vector.setMag(magnitude)
  }

  public fun heading(): Float {
    return this.vector.heading()
  }

  public fun copy(): Acceleration2D {
    return Acceleration2D(this.vector)
  }

  override fun equals(other: Any?): Boolean {
    return other is Acceleration2D && this.vector == other.vector
  }

  override fun hashCode(): Int {
    return vector.hashCode()
  }
}

