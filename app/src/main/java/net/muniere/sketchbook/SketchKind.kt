package net.muniere.sketchbook

public enum class SketchKind(
  public val rawValue: Int
) {
  EXAMPLE(0);

  public val seed: SketchSeed
    get() = when (this) {
      EXAMPLE -> SketchSeed(
        kind = this,
        title = "Example",
        caption = "Example",
      )
    }
}
