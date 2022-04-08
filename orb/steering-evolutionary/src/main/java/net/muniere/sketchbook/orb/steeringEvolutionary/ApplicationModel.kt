package net.muniere.sketchbook.orb.steeringEvolutionary

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D

internal final class ApplicationModel(
  frame: Rect2D,
  materials: List<MaterialModel>,
  vehicles: List<VehicleModel>,
  provider: MaterialProvider,
  stress: Float = 0.0F,
) {

  public var frame: Rect2D = frame
    private set

  public var materials: List<MaterialModel> = materials
    private set

  public var vehicles: List<VehicleModel> = vehicles
    private set

  public var provider: MaterialProvider = provider
    private set

  public var stress: Float = stress
    private set

  public val bounds: Rect2D
    get() = this.frame.copy(
      origin = Point2D.zero()
    )

  public fun hasNext(): Boolean {
    return this.materials.any { it.kind != MaterialKind.POISON } && this.vehicles.isNotEmpty()
  }

  public fun update() {
    this.materials += this.provider.create()

    this.vehicles.forEach { vehicle ->
      val coerced = vehicle.steerIn(
        rect = this.bounds,
        padding = vehicle.radius / 2,
      )
      if (coerced) {
        vehicle.penalty(this.stress)
        vehicle.update()
        return@forEach
      }

      val bestMaterial = this.materials
        .filter { vehicle.sensible(it) }
        .maxByOrNull { vehicle.evaluate(it) }

      when (bestMaterial) {
        null -> vehicle.steerRandomly()
        else -> vehicle.steerTo(bestMaterial)
      }

      vehicle.update()
    }

    this.vehicles.forEach { vehicle ->
      val collisions = this.materials.filter { vehicle.collides(it) }
      if (collisions.isEmpty()) {
        return@forEach
      }

      collisions.forEach { vehicle.consume(it) }
      this.materials -= collisions
    }

    this.vehicles.forEach { vehicle ->
      vehicle.penalty(this.stress)
    }

    val zombies = this.vehicles.filter { !it.isAlive }
    if (zombies.isEmpty()) {
      return
    }

    this.vehicles -= zombies

    val genome = this.vehicles.maxByOrNull { it.grade }.let(::checkNotNull).genome

    val clones = zombies.map { parent ->
      parent.clone(
        genome = genome.blurred(error = 0.05F)
      )
    }

    this.vehicles += clones
  }
}
