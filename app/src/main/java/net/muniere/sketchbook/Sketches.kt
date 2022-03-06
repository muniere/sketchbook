package net.muniere.sketchbook

import net.muniere.sketchbook.example.ExampleSketch
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.Sketch
import net.muniere.sketchbook.orb.starField.Sketch as StarFieldSketch

public final object Sketches {

  public fun create(kind: SketchKind, size: Size2D): Sketch {
    return when (kind) {
      SketchKind.EXAMPLE -> ExampleSketch(size)
      SketchKind.STAR_FIELD -> StarFieldSketch(size)
    }
  }
}
