package net.muniere.sketchbook.example

import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.Sketch

public final class ExampleSketch(size: Size2D) : Sketch(size) {

  override fun draw() {
    if (mousePressed) {
      circle(mouseX.toFloat(), mouseY.toFloat(), 50.0F)
    }
  }
}
