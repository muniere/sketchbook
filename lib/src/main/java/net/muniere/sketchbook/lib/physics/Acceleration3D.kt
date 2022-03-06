package net.muniere.sketchbook.lib.physics

import processing.core.PVector

public final class Acceleration3D(
  x: Float,
  y: Float,
  z: Float,
) {

  public constructor(vector: PVector) : this(vector.x, vector.y, vector.z)

  public companion object {
    public fun zero() = Acceleration3D(0.0F, 0.0F, 0.0F)
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

  public fun with(other: Acceleration3D): Acceleration3D {
    return Acceleration3D(other.vector)
  }

  public fun assign(other: Acceleration3D) {
    this.vector.set(other.vector)
  }

  public fun rotate(angle: Float): Acceleration3D {
    return Acceleration3D(this.vector.copy().rotate(angle))
  }

  public fun rotateAssign(angle: Float) {
    this.vector.rotate(angle)
  }

  public fun limit(magnitude: Float): Acceleration3D {
    return Acceleration3D(this.vector.copy().limit(magnitude))
  }

  public fun limitAssign(magnitude: Float) {
    this.vector.limit(magnitude)
  }

  public fun normalize(): Acceleration3D {
    return Acceleration3D(this.vector.copy().normalize())
  }

  public fun normalizeAssign() {
    this.vector.normalize()
  }

  public fun magnitude(): Float {
    return this.vector.mag()
  }

  public fun withMagnitude(magnitude: Float): Acceleration3D {
    return Acceleration3D(this.vector.copy().setMag(magnitude))
  }

  public fun setMagnitude(magnitude: Float) {
    this.vector.setMag(magnitude)
  }

  public fun heading(): Float {
    return this.vector.heading()
  }

  public fun copy(): Acceleration3D {
    return Acceleration3D(this.vector)
  }

  override fun equals(other: Any?): Boolean {
    return other is Acceleration3D && this.vector == other.vector
  }

  override fun hashCode(): Int {
    return vector.hashCode()
  }
}

