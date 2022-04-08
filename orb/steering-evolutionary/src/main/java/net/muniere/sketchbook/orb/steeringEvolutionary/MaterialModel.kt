package net.muniere.sketchbook.orb.steeringEvolutionary

import net.muniere.sketchbook.lib.graphics.Point2D
import kotlin.math.sign

internal class MaterialModel(
  public val radius: Float,
  public val center: Point2D,
  public val score: Float,
) {

  public val kind: MaterialKind
    get() = when (this.score.sign) {
      +1.0F -> MaterialKind.MEDICINE
      0.0F -> MaterialKind.NOTHING
      -1.0F -> MaterialKind.POISON
      else -> throw IllegalStateException()
    }
}
