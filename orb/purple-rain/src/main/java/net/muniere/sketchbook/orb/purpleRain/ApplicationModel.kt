package net.muniere.sketchbook.orb.purpleRain

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D

public final class ApplicationModel(
  public val frame: Rect2D,
  public val drops: List<DropModel>,
) {

  public val bounds: Rect2D
    get() = this.frame.with(
      origin = Point2D.zero()
    )

  public var color: Color
    get() = this.drops.first().color
    set(value) = this.drops.forEach { it.color = value }

  public fun update() {
    this.drops.forEach {
      it.update()

      if (it.point.y >= this.bounds.height) {
        it.reset()
      }
    }
  }

  public fun reset() {
    this.drops.forEach {
      it.reset()
    }
  }
}
