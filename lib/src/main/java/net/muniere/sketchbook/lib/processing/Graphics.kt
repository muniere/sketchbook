package net.muniere.sketchbook.lib.processing

import android.graphics.Color
import processing.core.PGraphics

public fun PGraphics.background(color: Color) {
  this.background(color.red() * 255, color.green() * 255, color.blue() * 255)
}

public fun PGraphics.stroke(color: Color) {
  this.stroke(color.red() * 255, color.green() * 255, color.blue() * 255)
}

public fun PGraphics.stroke(color: Color, alpha: Float) {
  this.stroke(color.red() * 255, color.green() * 255, color.blue() * 255, alpha * 255)
}

public fun PGraphics.fill(color: Color) {
  this.fill(color.red() * 255, color.green() * 255, color.blue() * 255)
}

public fun PGraphics.fill(color: Color, alpha: Float) {
  this.fill(color.red() * 255, color.green() * 255, color.blue() * 255, alpha * 255)
}
