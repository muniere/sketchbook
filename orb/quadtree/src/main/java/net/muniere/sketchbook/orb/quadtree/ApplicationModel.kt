package net.muniere.sketchbook.orb.quadtree

internal final class ApplicationModel(
  public val tree: TreeModel,
) {

  public val materials: List<MaterialModel>
    get() = this._materials

  private val _materials = mutableListOf<MaterialModel>()

  public fun add(material: MaterialModel) {
    this._materials.add(material)
  }

  public fun addAll(materials: Iterable<MaterialModel>) {
    this._materials.addAll(materials)
  }

  public fun walk(
    strategy: SearchStrategy = SearchStrategy.WIDTH_FIRST,
    callback: (division: DivisionModel) -> Unit,
  ) {
    this.tree.walk(strategy, callback)
  }

  // how can we re-balance tree instead of rebuild??
  public fun update() {
    val bounds = this.tree.boundary

    this.tree.clear()

    this._materials.forEach {
      it.update()
      it.bounceIn(bounds)
      this.tree.add(it)
    }

    this._materials.forEach { material ->
      val eyesight = this.tree.collect(
        strategy = SearchStrategy.DEPTH_FIRST,
        query = material.zone,
      )

      val collisions = eyesight.filter {
        it != material && it.intersects(material)
      }

      if (collisions.isEmpty()) {
        material.tag = MaterialTag.NORMAL
      } else {
        material.tag = MaterialTag.FOCUSED
        collisions.forEach {
          it.tag = MaterialTag.FOCUSED
        }
      }
    }
  }
}
