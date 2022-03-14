package net.muniere.sketchbook.lib.graphics

import processing.core.PGraphics
import processing.core.PImage

public fun PGraphics.translate(point: Point2D) {
  this.translate(point.x, point.y)
}

public fun PGraphics.translate(point: Point3D) {
  this.translate(point.x, point.y, point.z)
}

public fun PGraphics.line(p1: Point2D, p2: Point2D) {
  this.line(p1.x, p1.y, p2.x, p2.y)
}

public fun PGraphics.line(line: Line2D) {
  this.line(line.start.x, line.start.y, line.stop.x, line.stop.y)
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

public fun PGraphics.image(image: PImage, origin: Point2D) {
  this.image(image, origin.x, origin.y)
}

public fun PGraphics.image(image: PImage, frame: Rect2D) {
  this.image(image, frame.left, frame.top, frame.width, frame.height)
}

public fun PGraphics.vertex(point: Point2D) {
  this.vertex(point.x, point.y)
}

public fun PGraphics.vertex(point: Point3D) {
  this.vertex(point.x, point.y, point.z)
}
