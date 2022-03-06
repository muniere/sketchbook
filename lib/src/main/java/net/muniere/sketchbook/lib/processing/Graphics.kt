package net.muniere.sketchbook.lib.processing

import android.graphics.Color
import processing.core.PGraphics

public fun PGraphics.background(color: Color) {
  this.background(color.red(), color.green(), color.blue())
}

public fun PGraphics.stroke(color: Color) {
  this.stroke(color.red(), color.green(), color.blue())
}

public fun PGraphics.fill(color: Color) {
  this.fill(color.red(), color.green(), color.blue())
}
