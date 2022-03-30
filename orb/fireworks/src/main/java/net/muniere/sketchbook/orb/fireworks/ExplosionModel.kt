package net.muniere.sketchbook.orb.fireworks

import net.muniere.sketchbook.lib.physics.CircularMaterial

internal abstract class ExplosionModel {
  abstract fun perform(core: CircularMaterial): List<CircularMaterial>
}
