package net.muniere.sketchbook.lib.processing

import java.text.NumberFormat

public final class MetricsFormat {
  public var fields: List<MetricsField> = listOf(
    MetricsField.FRAME_COUNT,
    MetricsField.FRAME_RATE,
    MetricsField.MOUSE,
    MetricsField.KEY,
  )

  private val integerFormat = NumberFormat.getIntegerInstance()

  private val floatFormat = NumberFormat.getInstance().also {
    it.maximumFractionDigits = 1
    it.minimumFractionDigits = 1
  }

  public fun format(metrics: MetricsEntity): String {
    return this.fields.joinToString("\n") { field ->
      when (field) {
        MetricsField.FRAME_COUNT -> "Frame: ${this.integerFormat.format(metrics.frameCount)}"
        MetricsField.FRAME_RATE -> "FPS: ${this.floatFormat.format(metrics.frameRate)}"
        MetricsField.MOUSE -> "Mouse: (${metrics.mouseX}, ${metrics.mouseY})"
        MetricsField.KEY -> "Key: ${metrics.keyName} [${metrics.keyCode}]"
      }
    }
  }
}
