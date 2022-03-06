package net.muniere.sketchbook.example

import android.util.Size
import net.muniere.sketchbook.lib.processing.Sketch

public final class ExampleSketch(size: Size) : Sketch(size) {

  override fun draw() {
    if (mousePressed) {
      circle(mouseX.toFloat(), mouseY.toFloat(), 50.0F)
    }
  }
}
