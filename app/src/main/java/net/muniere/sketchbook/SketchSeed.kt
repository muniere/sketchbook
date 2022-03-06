package net.muniere.sketchbook

public final data class SketchSeed(
  public val kind: SketchKind,
  public val title: String,
  public val caption: String,
) {
  public val id: Int
    get() = this.kind.rawValue
}
