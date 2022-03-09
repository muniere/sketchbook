package net.muniere.sketchbook.orb.circleMorphing

import kotlin.math.absoluteValue

internal class ApplicationModel(
  public val morphing: MorphingModel,
) {
  private var sign: Int = 1

  public fun path(): PathModel {
    return this.morphing.path()
  }

  public fun update() {
    when (this.sign) {
      1 -> if (this.morphing.progress.absoluteValue > 0.999F) {
        this.sign = -1
      }
      -1 -> if (this.morphing.progress.absoluteValue < 0.001F) {
        this.sign = 1
      }
    }

    if (this.sign > 0) {
      this.morphing.forward()
    } else {
      this.morphing.backward()
    }
  }
}
