package net.muniere.sketchbook.lib.processing

import processing.core.PApplet

public interface SketchPlugin {
  public fun onPreDraw(applet: PApplet) {
    // do nothing
  }

  public fun onPostDraw(applet: PApplet) {
    // do nothing
  }
}
