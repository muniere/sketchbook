package net.muniere.sketchbook

import android.util.Size
import net.muniere.sketchbook.example.ExampleSketch
import net.muniere.sketchbook.lib.processing.Sketch

public final object Sketches {

  public fun create(kind: SketchKind, size: Size): Sketch {
    return when (kind) {
      SketchKind.EXAMPLE -> ExampleSketch(size)
    }
  }
}
