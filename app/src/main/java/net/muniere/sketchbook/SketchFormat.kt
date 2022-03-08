package net.muniere.sketchbook

public final object SketchFormat {

  public fun format(seed: SketchSeed): String {
    return "#${seed.id}: ${seed.title}"
  }
}
