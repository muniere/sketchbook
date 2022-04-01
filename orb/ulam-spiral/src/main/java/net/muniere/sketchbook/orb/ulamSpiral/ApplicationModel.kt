package net.muniere.sketchbook.orb.ulamSpiral

import net.muniere.sketchbook.lib.atlas.Line
import net.muniere.sketchbook.lib.math.Spot

internal final class ApplicationModel(
  public val piece: PieceModel,
) {

  private var ghost: Spot? = null

  public val stepCount: Int
    get() = this.piece.stepCount

  public val line: Line?
    get() {
      val ghost = this.ghost ?: run {
        return null
      }
      return Line(
        start = ghost,
        stop = this.piece.spot,
      )
    }

  public fun update() {
    this.ghost = this.piece.spot
    this.piece.forward()
  }
}
