package net.muniere.sketchbook

public enum class SketchKind(
  public val rawValue: Int,
) {
  EXAMPLE(0),
  STAR_FIELD(1);

  public val seed: SketchSeed
    get() = when (this) {
      EXAMPLE -> SketchSeed(
        kind = this,
        title = "Example",
        caption = "Example",
      )
      STAR_FIELD -> SketchSeed(
        kind = this,
        title = "Star Field",
        caption = "Star Field",
      )
    }
}
