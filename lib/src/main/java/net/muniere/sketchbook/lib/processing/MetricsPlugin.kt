package net.muniere.sketchbook.lib.processing

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.Alignment
import processing.core.PApplet
import processing.core.PGraphics

public final class MetricsPlugin(graphic: PGraphics) : Plugin {

  public var refreshRate: Int = 10

  public var padding: Float
    get() {
      return this.widget.padding
    }
    set(value) {
      this.widget.padding = value
    }

  public var alignment: Alignment
    get() {
      return this.widget.alignment
    }
    set(value) {
      this.widget.alignment = value
    }

  public var textColor: Color
    get() {
      return this.widget.textColor
    }
    set(value) {
      this.widget.textColor = value
    }

  public var textSize: Float
    get() {
      return this.widget.textSize
    }
    set(value) {
      this.widget.textSize = value
    }

  private val widget = MetricsWidget(graphic)

  override fun onPostDraw(applet: PApplet) {
    if (applet.frameCount % this.refreshRate == 0) {
      this.widget.model = MetricsEntity.parse(applet)
    }

    this.widget.draw()
  }
}
