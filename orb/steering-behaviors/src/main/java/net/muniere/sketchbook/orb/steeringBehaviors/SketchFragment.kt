package net.muniere.sketchbook.orb.steeringBehaviors

import processing.android.PFragment

public final class SketchFragment : PFragment() {
  init {
    this.sketch = SketchApplet()
  }
}
