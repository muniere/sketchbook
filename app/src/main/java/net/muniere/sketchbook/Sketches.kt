package net.muniere.sketchbook

import net.muniere.sketchbook.example.ExampleSketch
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.Sketch

public final object Sketches {

  public fun create(kind: SketchKind, size: Size2D): Sketch {
    return when (kind) {
      SketchKind.EXAMPLE -> ExampleSketch(size)
    }
  }
}
