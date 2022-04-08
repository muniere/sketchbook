package net.muniere.sketchbook.orb.steeringEvolutionary

internal final data class VehicleGenome(
  public val weight: WeightModel,
  public val sensor: SensorModel,
) {

  public companion object {
    public fun random(): VehicleGenome {
      return VehicleGenome(
        weight = WeightModel.random(),
        sensor = SensorModel.random(),
      )
    }
  }

  public fun blurred(error: Float = 0.01F): VehicleGenome {
    return VehicleGenome(
      weight = this.weight.blurred(error = error),
      sensor = this.sensor.blurred(error = error),
    )
  }
}
