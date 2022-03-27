package net.muniere.sketchbook.orb.travelingSalesperson

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.text
import net.muniere.sketchbook.lib.math.Fraction
import net.muniere.sketchbook.lib.processing.ModelWidget
import net.muniere.sketchbook.lib.processing.fill
import processing.core.PConstants
import processing.core.PGraphics
import java.text.NumberFormat

internal final class ProgressWidget(graphics: PGraphics) : ModelWidget<Fraction>(graphics) {
  private val integerFormat = NumberFormat.getIntegerInstance()
  private val percentageFormat = NumberFormat.getPercentInstance().also {
    it.minimumFractionDigits = 3
    it.maximumFractionDigits = 3
  }

  public var origin: Point2D = Point2D.zero()
  public var textSize: Float = 32.0F
  public var textColor: Color = Colors.WHITE

  override fun doDraw(model: Fraction) {
    val numerator = this.integerFormat.format(model.numerator)
    val denominator = this.integerFormat.format(model.denominator)
    val percentage = this.percentageFormat.format(model.toFloat())
    val label = """
      $numerator / $denominator
      (${percentage})
    """.trimIndent()

    this.scope {
      it.noStroke()
      it.fill(this.textColor)
      it.textAlign(PConstants.RIGHT, PConstants.TOP)
      it.textSize(this.textSize)
      it.text(label, this.origin)
    }
  }
}
