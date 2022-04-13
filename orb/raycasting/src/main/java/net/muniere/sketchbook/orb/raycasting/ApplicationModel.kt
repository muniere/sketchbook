package net.muniere.sketchbook.orb.raycasting

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D

internal final class ApplicationModel(
  public val frame: Rect2D,
  public val walls: List<WallModel>,
  public val particle: ParticleModel,
) {

  public val bounds: Rect2D
    get() = this.frame.copy(
      origin = Point2D.zero(),
    )

  public fun update() {
    this.particle.update()
    this.particle.bounceIn(this.bounds)
    this.particle.cast(this.walls)
  }

  public fun warpTo(point: Point2D) {
    this.particle.moveTo(point)
  }
}
