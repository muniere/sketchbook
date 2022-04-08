package net.muniere.sketchbook.orb.steeringEvolutionary

import kotlin.random.Random

internal final data class SensorModel(
  public val rewardSight: Float,
  public val penaltySight: Float,
) {

  public companion object {
    public fun random(): SensorModel {
      return SensorModel(
        rewardSight = Random.nextFloat(),
        penaltySight = -1.0F * Random.nextFloat(),
      )
    }
  }

  public fun blurred(error: Float = 0.01F): SensorModel {
    return SensorModel(
      rewardSight = this.rewardSight * (1.0F + (Random.nextFloat() - 0.5F) * error),
      penaltySight = this.penaltySight * (1.0F + (Random.nextFloat() - 0.5F) * error),
    )
  }
}
