package net.muniere.sketchbook.orb.bezierCurve

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D

internal class ApplicationModel(
  public val frame: Rect2D,
  public val vehicles: List<VehicleModel>,
  public val calculator: CalculatorModel,
) {
  public var bezier: PathModel? = null
    private set

  public val bounds: Rect2D
    get() = this.frame.copy(origin = Point2D.zero())

  public fun update() {
    this.vehicles.forEach {
      it.update()
      it.bounceIn(this.bounds)
    }

    this.calculator.controls = this.vehicles.map { it.center }
    this.bezier = this.calculator.run()
  }
}
