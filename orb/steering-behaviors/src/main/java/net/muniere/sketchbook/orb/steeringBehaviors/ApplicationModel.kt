package net.muniere.sketchbook.orb.steeringBehaviors

import net.muniere.sketchbook.lib.graphics.Point2D

internal final class ApplicationModel(
  public val vehicles: List<VehicleModel>,
) {

  public var repulsion: Point2D? = null

  public fun update() {
    this.vehicles.forEach {
      it.update()
      it.attract()

      val repulsion = this.repulsion
      if (repulsion != null) {
        it.repulse(repulsion)
      }
    }
  }
}
