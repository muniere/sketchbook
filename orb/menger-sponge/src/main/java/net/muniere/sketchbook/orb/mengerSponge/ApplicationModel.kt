package net.muniere.sketchbook.orb.mengerSponge

internal final class ApplicationModel(
  public val sponge: SpongeModel,
) {
  public fun update() {
    this.sponge.cycle()
  }

  public fun rotate(value: Float) {
    this.sponge.rotate(value)
  }
}

