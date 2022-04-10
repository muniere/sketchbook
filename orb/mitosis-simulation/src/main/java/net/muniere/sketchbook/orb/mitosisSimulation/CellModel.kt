package net.muniere.sketchbook.orb.mitosisSimulation

import android.graphics.Color
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import processing.core.PVector

internal class CellModel(
  center: Point2D,
  radius: Float,
  growth: Float,
  limit: Float,
) {

  public var center: Point2D = center
    private set

  public var radius: Float = radius
    private set

  public var velocity: PVector = PVector()
    private set
  public var growth: Float = growth
    private set

  public var limit: Float = limit
    private set

  public var fillColor: Color = Colors.parse("#FFFFFF")
  public var strokeColor: Color = Colors.parse("#FFFFFF")

  public fun update() {
    val accel = PVector.random2D().div(5.0F)
    this.velocity = PVector.add(this.velocity, accel).normalize()
    this.center = this.center.movingBy(x = this.velocity.x, y = this.velocity.y)
    this.radius = (this.radius * this.growth).coerceAtMost(this.limit)
  }

  public fun split(): List<CellModel> {
    val children = listOf(
      CellModel(
        center = this.center.movingBy(x = -this.radius / 4),
        radius = this.radius / 2,
        growth = this.growth,
        limit = this.limit,
      ),
      CellModel(
        center = this.center.movingBy(x = this.radius / 4),
        radius = this.radius / 2,
        growth = this.growth,
        limit = this.limit,
      ),
    )

    children.forEach {
      it.fillColor = this.fillColor
    }

    return children
  }

  public fun contains(point: Point2D): Boolean {
    return Point2D.dist(this.center, point) < this.radius
  }

  public fun constraint(rect: Rect2D) {
    this.center = this.center.copy(
      x = this.center.x.coerceIn(rect.left + this.radius, rect.right - this.radius),
      y = this.center.y.coerceIn(rect.top + this.radius, rect.bottom - this.radius),
    )
  }
}
