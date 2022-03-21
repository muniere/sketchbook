package net.muniere.sketchbook.orb.quadtree

import net.muniere.sketchbook.lib.graphics.Rect2D

internal final class TreeModel(
  private val root: DivisionModel,
) {

  public companion object {
    public fun create(boundary: Rect2D, capacity: Int): TreeModel {
      val root = DivisionModel(
        boundary = boundary,
        capacity = capacity,
      )
      return TreeModel(root)
    }
  }

  public val boundary: Rect2D
    get() = this.root.boundary

  public val capacity: Int
    get() = this.root.capacity

  public fun add(material: MaterialModel) {
    this.root.add(material)
  }

  public fun collect(strategy: SearchStrategy, query: Rect2D? = null): List<MaterialModel> {
    return when (strategy) {
      SearchStrategy.DEPTH_FIRST -> this.collectDeeply(query)
      SearchStrategy.WIDTH_FIRST -> this.collectWidely(query)
    }
  }

  private fun collectDeeply(query: Rect2D? = null): List<MaterialModel> {
    val stack = mutableListOf<DivisionModel>(this.root)
    val divisions = mutableListOf<DivisionModel>()

    while (stack.size > 0) {
      val division = stack.removeLast()

      if (query != null && !division.boundary.intersects(query)) {
        continue
      }

      val children = division.children
      if (children.isNotEmpty()) {
        stack.addAll(children.reversed())
      } else {
        divisions.add(division)
      }
    }

    return divisions.flatMap { division ->
      when (query) {
        null -> division.materials
        else -> division.materials.filter { query.contains(it.center) }
      }
    }
  }

  private fun collectWidely(query: Rect2D? = null): List<MaterialModel> {
    val queue = mutableListOf<DivisionModel>(this.root)
    val divisions = mutableListOf<DivisionModel>()

    while (queue.size > 0) {
      val division = queue.removeFirst()

      if (query != null && !division.boundary.intersects(query)) {
        continue
      }

      val children = division.children
      if (children.isNotEmpty()) {
        queue.addAll(children)
      } else {
        divisions.add(division)
      }
    }

    return divisions.flatMap { division ->
      when (query) {
        null -> division.materials
        else -> division.materials.filter { query.contains(it.center) }
      }
    }
  }

  public fun walk(strategy: SearchStrategy, callback: (division: DivisionModel) -> Unit) {
    when (strategy) {
      SearchStrategy.DEPTH_FIRST -> this.walkDeeply(callback)
      SearchStrategy.WIDTH_FIRST -> this.walkWidely(callback)
    }
  }

  private fun walkDeeply(callback: (division: DivisionModel) -> Unit) {
    val stack = mutableListOf(this.root)

    while (stack.size > 0) {
      val division = stack.removeLast()

      callback(division)

      stack.addAll(division.children.reversed())
    }
  }

  private fun walkWidely(callback: (division: DivisionModel) -> Unit) {
    val queue = mutableListOf(this.root)

    while (queue.size > 0) {
      val division = queue.removeFirst()

      callback(division)

      queue.addAll(division.children)
    }
  }

  public fun clear() {
    this.root.clear()
  }
}
