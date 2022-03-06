package net.muniere.sketchbook.orb.starField

internal final class StarFieldModel(
  stars: List<StarModel>,
) {

  public val stars: List<StarModel>
    get() = this.children

  private val children: MutableList<StarModel> = stars.toMutableList()

  public fun setSpeed(value: Float) {
    this.children.forEach { it.speed = value }
  }

  public fun addAll(vararg stars: StarModel) {
    this.children.addAll(stars)
  }

  public fun update() {
    this.children.forEach { it.update() }
  }

  public fun reset() {
    this.children.forEach { it.reset() }
  }
}
