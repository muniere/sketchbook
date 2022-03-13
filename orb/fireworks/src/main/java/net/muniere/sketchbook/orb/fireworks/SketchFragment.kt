package net.muniere.sketchbook.orb.fireworks

import processing.android.PFragment

public final class SketchFragment : PFragment() {
  init {
    this.sketch = SketchApplet()
  }
}
