package net.muniere.sketchbook.lib.graphics

import processing.core.PGraphics

public fun PGraphics.line(p1: Point2D, p2: Point2D) {
  this.line(p1.x, p1.y, p2.x, p2.y)
}

public fun PGraphics.square(point: Point2D, extent: Float) {
  this.square(point.x, point.y, extent)
}

public fun PGraphics.rect(point: Point2D, size: Size2D) {
  this.rect(point.x, point.y, size.width, size.height)
}

public fun PGraphics.rect(rect: Rect2D) {
  this.rect(rect.left, rect.top, rect.width, rect.height)
}

public fun PGraphics.circle(center: Point2D, extent: Float) {
  this.circle(center.x, center.y, extent)
}
