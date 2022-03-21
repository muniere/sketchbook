package net.muniere.sketchbook.lib.processing

import processing.android.PFragment
import processing.core.PApplet

public open class SFragment : PFragment {

  constructor() : super()
  constructor(sketch: PApplet) : super(sketch)

  public fun requireSketch(): SApplet {
    return this.sketch as SApplet
  }

  public inline fun <reified Sketch : PApplet> requireSketch(): Sketch {
    return this.sketch as Sketch
  }
}
