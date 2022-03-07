package net.muniere.sketchbook

public enum class SketchKind(
  public val rawValue: Int,
) {
  STAR_FIELD(1),
  MENGER_SPONGE(2),;

  public val seed: SketchSeed
    get() = when (this) {
      STAR_FIELD -> SketchSeed(
        kind = this,
        title = "Star Field",
        caption = "Star Field",
      )
      MENGER_SPONGE -> SketchSeed(
        kind = this,
        title = "Menger Sponge",
        caption = "Menger Sponge",
      )
    }
}
