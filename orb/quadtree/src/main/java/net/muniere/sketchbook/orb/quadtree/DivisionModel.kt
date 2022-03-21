package net.muniere.sketchbook.orb.quadtree

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D

internal final class DivisionModel(
  public val boundary: Rect2D,
  public val capacity: Int,
) {
  public var materials: List<MaterialModel> = listOf()
    private set

  public var children: List<DivisionModel> = listOf()
    private set

  public fun add(material: MaterialModel): Boolean {
    if (!this.boundary.contains(material.center)) {
      return false
    }

    if (this.children.isEmpty() && this.materials.size < this.capacity) {
      this.materials = this.materials + material
      return true
    }

    if (this.children.isEmpty()) {
      this.subdivide()

      this.materials.forEach { x ->
        this.children.firstOrNull { it.contains(x.center) }?.add(x)
      }

      this.materials = emptyList()
    }

    this.children.firstOrNull { it.contains(material.center) }?.add(material)
    return true
  }

  public fun contains(point: Point2D): Boolean {
    return this.boundary.contains(point)
  }

  public fun clear() {
    this.materials = emptyList()
    this.children = emptyList()
  }

  private fun subdivide() {
    val origins = listOf(
      Point2D(
        x = this.boundary.origin.x,
        y = this.boundary.origin.y,
      ),
      Point2D(
        x = this.boundary.center.x,
        y = this.boundary.origin.y,
      ),
      Point2D(
        x = this.boundary.origin.x,
        y = this.boundary.center.y,
      ),
      Point2D(
        x = this.boundary.center.x,
        y = this.boundary.center.y,
      ),
    )

    this.children = origins.map { origin ->
      DivisionModel(
        boundary = Rect2D(
          origin = origin,
          size = this.boundary.size * 0.5F,
        ),
        capacity = this.capacity,
      )
    }
  }
}
