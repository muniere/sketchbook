package net.muniere.sketchbook.orb.steeringEvolutionary

import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.physics.Acceleration2D
import net.muniere.sketchbook.lib.physics.Force2D
import net.muniere.sketchbook.lib.physics.Velocity2D
import processing.core.PVector

internal final class VehicleModel(
  score: Float = 0.0F,
  radius: Float = 20.0F,
  center: Point2D = Point2D.zero(),
  velocity: Velocity2D = Velocity2D.zero(),
  acceleration: Acceleration2D = Acceleration2D.zero(),
  genome: VehicleGenome = VehicleGenome.random(),
) {

  public var radius: Float = radius
    private set

  public var center: Point2D = center
    private set

  public var velocity: Velocity2D = velocity
    private set

  public var acceleration: Acceleration2D = acceleration
    private set

  public var genome: VehicleGenome = genome
    private set

  public var score: Float = score
    private set

  public var scoreRange = FloatRange(0.0F, score)
    private set

  public var generation: Int = 1
    private set

  public var age: Int = 0
    private set

  public val weight: WeightModel
    get() = this.genome.weight

  public val sensor: SensorModel
    get() = this.genome.sensor

  public val isAlive: Boolean
    get() = this.score > 0

  public val scoreFraction: Float
    get() = this.score / (this.scoreRange.endInclusive - this.scoreRange.start)

  public val grade: Float
    get() = this.age * this.scoreFraction

  public fun collides(material: MaterialModel): Boolean {
    return this.distanceTo(material) < this.radius
  }

  public fun distanceTo(material: MaterialModel): Float {
    return Point2D.dist(this.center, material.center)
  }

  public fun steerTo(material: MaterialModel) {
    val dx = material.center.x - this.center.x
    val dy = material.center.y - this.center.y

    val currentVelocity = this.velocity
    val desiredVelocity = Velocity2D(dx, dy).withMagnitude(VehicleConstraints.MAX_SPEED)

    val deltaVector = PVector.sub(
      desiredVelocity.toVector(),
      currentVelocity.toVector(),
    )

    val force = Force2D(deltaVector).limiting(VehicleConstraints.MAX_FORCE)

    this.applyForce(force)
  }

  public fun steerIn(rect: Rect2D, padding: Float? = null): Boolean {
    val inset = padding ?: 0.0F

    val currentVelocity = this.velocity
    val desiredVelocity = let {
      var x: Float? = null
      var y: Float? = null

      if (this.center.x < inset && currentVelocity.x < 0) {
        x = -currentVelocity.x
      }
      if (this.center.x > rect.width - inset && currentVelocity.x > 0) {
        x = -currentVelocity.x
      }
      if (this.center.y < inset && currentVelocity.y < 0) {
        y = -currentVelocity.y
      }
      if (this.center.y > rect.height - inset && currentVelocity.y > 0) {
        y = -currentVelocity.y
      }

      return@let Velocity2D(
        x = x ?: currentVelocity.x,
        y = y ?: currentVelocity.y,
      )
    }

    if (currentVelocity == desiredVelocity) {
      return false
    }

    desiredVelocity.setMagnitude(VehicleConstraints.MAX_SPEED)

    val deltaVector = PVector.sub(
      desiredVelocity.toVector(),
      currentVelocity.toVector(),
    )

    val force = Force2D(deltaVector).limiting(VehicleConstraints.MAX_FORCE)

    this.applyForce(force)
    return true
  }

  public fun steerRandomly() {
    val range = FloatRange(-1.0F, 1.0F)

    val x = range.random()
    val y = range.random()

    val force = Force2D(x, y)
      .normalized()
      .withMagnitude(VehicleConstraints.MAX_FORCE)

    this.applyForce(force)
  }

  public fun applyForce(force: Force2D) {
    this.acceleration.assign(
      force.acceleration(mass = 1.0F)
    )
  }

  public fun sensible(material: MaterialModel): Boolean {
    val distance = Point2D.dist(this.center, material.center)

    when (material.kind) {
      MaterialKind.MEDICINE -> {
        return distance < this.sensor.rewardSight
      }
      MaterialKind.POISON -> {
        return distance < this.sensor.penaltySight
      }
      MaterialKind.NOTHING -> {
        return false
      }
    }
  }

  public fun evaluate(material: MaterialModel): Float {
    val distance = Point2D.dist(this.center, material.center)

    when (material.kind) {
      MaterialKind.MEDICINE -> {
        return this.weight.rewardFactor / (distance + 1)
      }
      MaterialKind.POISON -> {
        return this.weight.penaltyFactor / (distance + 1)
      }
      MaterialKind.NOTHING -> {
        return (this.weight.rewardFactor + this.weight.penaltyFactor) / (distance + 1)
      }
    }
  }

  public fun consume(material: MaterialModel) {
    this.score = (this.score + material.score).coerceIn(this.scoreRange)
  }

  public fun penalty(value: Float) {
    this.score = (this.score - value).coerceIn(this.scoreRange)
  }

  public fun update() {
    this.velocity.add(this.acceleration)
    this.velocity.limit(VehicleConstraints.MAX_SPEED)
    this.center.add(this.velocity.toMove())
    this.acceleration.setMagnitude(0.0F)
    this.age += 1
  }

  public fun clone(genome: VehicleGenome): VehicleModel {
    return VehicleModel(
      score = this.scoreRange.endInclusive,
      radius = this.radius,
      center = this.center.copy(),
      velocity = this.velocity.copy(),
      acceleration = this.acceleration.copy(),
      genome = genome,
    ).also {
      it.generation = this.generation + 1
    }
  }
}
