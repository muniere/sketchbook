package net.muniere.sketchbook.orb.starField

internal final class ApplicationModel(
  public val starField: StarFieldModel,
) {
  constructor(stars: List<StarModel>) : this(StarFieldModel(stars))

  public fun update() {
    this.starField.update()
  }
}
