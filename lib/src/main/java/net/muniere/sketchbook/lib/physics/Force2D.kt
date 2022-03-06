package net.muniere.sketchbook.lib.physics

import processing.core.PVector

public final class Force2D(
  x: Float,
  y: Float,
) {

  public constructor(vector: PVector) : this(vector.x, vector.y)

  companion object {
    public fun zero() = Force2D(0.0F, 0.0F)
  }

  private val vector = PVector(x, y, 0.0F)

  public val x: Float
    get() = this.vector.x

  public val y: Float
    get() = this.vector.y

  public fun toVector(): PVector {
    return this.vector.copy()
  }

  public fun acceleration(mass: Float): Acceleration2D {
    return Acceleration2D(this.vector.copy().div(mass))
  }

  public operator fun plus(delta: Force2D): Force2D {
    return Force2D(this.vector.copy().add(delta.toVector()))
  }

  public operator fun plusAssign(delta: Force2D) {
    this.vector.add(delta.vector)
  }

  public operator fun minus(delta: Force2D): Force2D {
    return Force2D(this.vector.copy().sub(delta.toVector()))
  }

  public operator fun minusAssign(delta: Force2D) {
    this.vector.sub(delta.vector)
  }

  public operator fun times(value: Float): Force2D {
    return Force2D(this.vector.copy().mult(value))
  }

  public operator fun timesAssign(value: Float) {
    this.vector.mult(value)
  }

  public fun with(value: Force2D): Force2D {
    return Force2D(value.vector)
  }

  public fun assign(value: Force2D) {
    this.vector.set(value.vector)
  }

  public fun rotate(angle: Float): Force2D {
    return Force2D(this.vector.copy().rotate(angle))
  }

  public fun rotateAssign(angle: Float) {
    this.vector.rotate(angle)
  }

  public fun limit(magnitude: Float): Force2D {
    return Force2D(this.vector.copy().limit(magnitude))
  }

  public fun limitAssign(magnitude: Float) {
    this.vector.limit(magnitude)
  }

  public fun normalize(): Force2D {
    return Force2D(this.vector.copy().normalize())
  }

  public fun normalizeAssign() {
    this.vector.normalize()
  }

  public fun magnitude(): Float {
    return this.vector.mag()
  }

  public fun withMagnitude(magnitude: Float): Force2D {
    return Force2D(this.vector.copy().setMag(magnitude))
  }

  public fun setMagnitude(magnitude: Float) {
    this.vector.setMag(magnitude)
  }

  public fun heading(): Float {
    return this.vector.heading()
  }

  public fun copy(): Force2D {
    return Force2D(this.vector)
  }

  override fun equals(other: Any?): Boolean {
    return other is Force2D && this.vector == other.vector
  }

  override fun hashCode(): Int {
    return vector.hashCode()
  }
}
