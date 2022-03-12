package net.muniere.sketchbook.lib.processing

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Alignment
import net.muniere.sketchbook.lib.graphics.Gravity
import processing.core.PConstants
import processing.core.PGraphics

public final class MetricsWidget(graphics: PGraphics) : ModelWidget<MetricsEntity>(graphics) {
  public var padding: Float = 8.0F
  public var alignment: Alignment = Alignment.TOP_LEFT
  public var textColor: Color = Colors.WHITE
  public var textSize: Float = 32.0F

  private val format = MetricsFormat()

  public val x: Float
    get() = this.g.width / 2 + (this.g.width / 2 - this.padding) * this.gravity.x

  public val y: Float
    get() = this.g.height / 2 + (this.g.height / 2 - this.padding) * this.gravity.y

  private val gravity
    get() = Gravity(
      x = when (this.alignment.x) {
        PConstants.LEFT -> -1
        PConstants.CENTER -> 0
        PConstants.RIGHT -> 1
        else -> 0
      },
      y = when (this.alignment.y) {
        PConstants.TOP -> -1
        PConstants.CENTER -> 0
        PConstants.BOTTOM -> 1
        else -> 0
      }
    )

  override fun doDraw(model: MetricsEntity) {
    val label = this.format.format(model)

    this.scope {
      it.fill(this.textColor)
      it.textSize(this.textSize)
      it.textAlign(this.alignment.x, this.alignment.y)
      it.text(label, this.x, this.y)
    }
  }
}
