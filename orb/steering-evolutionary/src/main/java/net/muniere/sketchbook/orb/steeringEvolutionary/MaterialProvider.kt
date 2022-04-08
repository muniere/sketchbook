package net.muniere.sketchbook.orb.steeringEvolutionary

import net.muniere.sketchbook.lib.processing.FrameClock

internal class MaterialProvider(
  private val clock: FrameClock,
  private val interval: Int,
  private val factory: () -> List<MaterialModel>,
) {
  public fun create(): List<MaterialModel> {
    if (this.clock.time.toInt() % this.interval == 0) {
      return this.factory.invoke()
    } else {
      return emptyList()
    }
  }
}
