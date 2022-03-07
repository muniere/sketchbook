package net.muniere.sketchbook.lib.processing

import processing.core.PApplet

public interface Plugin {
  public fun onPreDraw(applet: PApplet) {
    // do nothing
  }

  public fun onPostDraw(applet: PApplet) {
    // do nothing
  }
}
