package net.muniere.sketchbook.orb.steeringEvolutionary

import kotlin.random.Random

internal final data class WeightModel(
  public val rewardFactor: Float,
  public val penaltyFactor: Float,
) {

  public companion object {
    public fun random(): WeightModel {
      return WeightModel(
        rewardFactor = Random.nextFloat(),
        penaltyFactor = -1.0F * Random.nextFloat(),
      )
    }
  }

  public fun blurred(error: Float = 0.01F): WeightModel {
    return WeightModel(
      rewardFactor = this.rewardFactor * (1.0F + (Random.nextFloat() - 0.5F) * error),
      penaltyFactor = this.penaltyFactor * (1.0F + (Random.nextFloat() - 0.5F) * error),
    )
  }
}
