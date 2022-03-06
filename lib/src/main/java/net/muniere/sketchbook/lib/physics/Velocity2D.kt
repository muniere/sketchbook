package net.muniere.sketchbook.lib.physics

import processing.core.PVector

public final class Velocity2D(
  x: Float,
  y: Float,
  z: Float,
) {

  public constructor(vector: PVector) : this(vector.x, vector.y, vector.z)

  public companion object {
    public fun zero() = Velocity2D(0.0F, 0.0F, 0.0F)
  }

  private val vector = PVector(x, y, z)

  public val x: Float
    get() = this.vector.x

  public val y: Float
    get() = this.vector.y

  public val z: Float
    get() = this.vector.z

  public fun toVector(): PVector {
    return this.vector.copy()
  }

  public operator fun plus(delta: Acceleration2D): Velocity2D {
    return Velocity2D(this.vector.copy().add(delta.toVector()))
  }

  public operator fun plusAssign(delta: Acceleration2D) {
    this.vector.add(delta.toVector())
  }

  public operator fun minus(delta: Acceleration2D): Velocity2D {
    return Velocity2D(this.vector.copy().sub(delta.toVector()))
  }

  public operator fun minusAssign(delta: Acceleration2D) {
    this.vector.sub(delta.toVector())
  }

  public fun rotate(angle: Float): Velocity2D {
    return Velocity2D(this.vector.copy().rotate(angle))
  }

  public fun rotateAssign(angle: Float) {
    this.vector.rotate(angle)
  }

  public fun limit(magnitude: Float): Velocity2D {
    return Velocity2D(this.vector.copy().limit(magnitude))
  }

  public fun limitAssign(magnitude: Float) {
    this.vector.limit(magnitude)
  }

  public fun normalize(): Velocity2D {
    return Velocity2D(this.vector.copy().normalize())
  }

  public fun normalizeAssign() {
    this.vector.normalize()
  }

  public fun magnitude(): Float {
    return this.vector.mag()
  }

  public fun withMagnitude(magnitude: Float): Velocity2D {
    return Velocity2D(this.vector.copy().setMag(magnitude))
  }

  public fun setMagnitude(magnitude: Float) {
    this.vector.setMag(magnitude)
  }

  public fun heading(): Float {
    return this.vector.heading()
  }

  public fun copy(): Velocity2D {
    return Velocity2D(this.vector)
  }

  override fun equals(other: Any?): Boolean {
    return other is Velocity2D && this.vector == other.vector
  }

  override fun hashCode(): Int {
    return vector.hashCode()
  }
}
