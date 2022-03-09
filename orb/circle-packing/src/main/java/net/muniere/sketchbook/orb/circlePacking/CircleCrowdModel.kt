package net.muniere.sketchbook.orb.circlePacking

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D

internal final class CircleCrowdModel(
  public val frame: Rect2D,
) {

  public val circles: List<CircleModel>
    get() = this.children

  private var children = mutableListOf<CircleModel>()

  public fun includes(point: Point2D): Boolean {
    return this.children.any { it.includes(point) }
  }

  public fun intersects(circle: CircleModel): Boolean {
    return this.children.any { it.intersects(circle) }
  }

  public fun trySpawn(radius: Float, center: Point2D): Boolean {
    val circle = CircleModel(
      center = center,
      radius = radius,
    ).also {
      it.startGrow()
    }

    if (circle.intersects(this.children)) {
      return false
    }

    this.children.add(circle)
    return true
  }

  public fun update() {
    this.children.forEach {
      if (it.isGrowing && (it.overflows(this.frame) || it.intersects(this.children))) {
        it.stopGrow()
      }
      it.update()
    }
  }
}
