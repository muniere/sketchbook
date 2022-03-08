package net.muniere.sketchbook.orb.fireworks

import net.muniere.sketchbook.lib.physics.CircularMaterial

internal data class FireSeedModel(
  internal val core: CircularMaterial,
  internal val lifespan: Int,
)
