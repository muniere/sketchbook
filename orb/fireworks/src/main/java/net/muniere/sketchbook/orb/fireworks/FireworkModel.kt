package net.muniere.sketchbook.orb.fireworks

import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.physics.Acceleration2D
import net.muniere.sketchbook.lib.physics.CircularMaterial
import net.muniere.sketchbook.lib.physics.Force2D

internal final class FireworkModel(
  private var explosion: ExplosionModel,
) {
  private var core: CircularMaterial? = null
  private var petals: List<CircularMaterial> = emptyList()
  private var lifespan: Int = 0
  private var age: Int = 0

  public val particles: List<CircularMaterial>
    get() = listOfNotNull(this.core) + this.petals

  public val remaining: Float
    get() = (this.lifespan - this.age).toFloat() / this.lifespan

  public val isActive: Boolean
    get() = this.core != null || this.petals.isNotEmpty()

  public fun ignite(seed: FireSeedModel) {
    this.core = seed.core
    this.lifespan = seed.lifespan
    this.age = 0
  }

  public fun apply(gravity: Acceleration2D) {
    this.particles.forEach {
      val force = Force2D(
        x = gravity.x * it.mass,
        y = gravity.y * it.mass,
      )
      it.apply(force)
    }
  }

  public fun update() {
    val core = this.core

    if (core != null) {
      core.update()

      if (core.velocity.y >= 0) {
        this.core = null
        this.petals = this.explosion.perform(core)
      } else {
        // keep going
      }
    }

    this.petals.forEach {
      it.update()
    }

    this.age += 1

    if (this.age > this.lifespan) {
      this.die()
    }
  }

  public fun ensureIn(rect: Rect2D) {
    if (this.particles.all { it.top > rect.bottom }) {
      this.die()
    }
  }

  private fun die() {
    this.core = null
    this.petals = emptyList()
  }
}
