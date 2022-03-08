package net.muniere.sketchbook.orb.fireworks

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.physics.Acceleration2D

internal final class ApplicationModel(
  public val frame: Rect2D,
  public val gravity: Acceleration2D,
  public val ignition: IgnitionModel,
  public val fireworks: List<FireworkModel>,
) {

  public val bounds: Rect2D
    get() = this.frame.copy(origin = Point2D.zero())

  public fun update() {
    this.fireworks.forEach {
      if (it.isActive) {
        it.ensureIn(this.bounds)
      }
      if (!it.isActive) {
        val particle = this.ignition.performIn(this.bounds)
        it.ignite(particle)
      }
      if (it.isActive) {
        it.apply(this.gravity)
        it.update()
      }
    }
  }
}
