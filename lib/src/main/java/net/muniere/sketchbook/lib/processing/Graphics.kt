package net.muniere.sketchbook.lib.processing

import android.graphics.Color
import processing.core.PGraphics

public fun PGraphics.background(color: Color) {
  this.background(
    color.red() * this.colorModeX,
    color.green() * this.colorModeY,
    color.blue() * this.colorModeZ,
    color.alpha() * this.colorModeA,
  )
}

public fun PGraphics.stroke(color: Color?) {
  when (color) {
    null -> this.noStroke()
    else -> this.stroke(
      color.red() * this.colorModeX,
      color.green() * this.colorModeY,
      color.blue() * this.colorModeZ,
      color.alpha() * this.colorModeA,
    )
  }
}

public fun PGraphics.fill(color: Color?) {
  when (color) {
    null -> this.noFill()
    else -> this.fill(
      color.red() * this.colorModeX,
      color.green() * this.colorModeY,
      color.blue() * this.colorModeZ,
      color.alpha() * this.colorModeA,
    )
  }
}
