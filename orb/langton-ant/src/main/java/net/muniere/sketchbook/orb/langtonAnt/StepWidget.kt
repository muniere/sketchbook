package net.muniere.sketchbook.orb.langtonAnt

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.text
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import processing.core.PConstants
import processing.core.PGraphics
import java.text.NumberFormat

internal final class StepWidget(graphics: PGraphics) : ModelWidget<StepModel>(graphics) {
  public var origin: Point2D = Point2D.zero()
  public var color: Color = Colors.BLACK
  public var textSize: Float = 32.0F
  public var textAlignX = PConstants.RIGHT
  public var textAlignY = PConstants.TOP

  private val format = NumberFormat.getIntegerInstance()

  override fun doDraw(model: StepModel) {
    val label = "${this.format.format(model.value)} Steps"

    this.scope {
      it.fill(this.color)
      it.textSize(this.textSize)
      it.textAlign(this.textAlignX, this.textAlignY)
      it.text(label, this.origin)
    }
  }
}
