package net.muniere.sketchbook.orb.raycasting

import android.graphics.Color
import net.muniere.sketchbook.lib.FloatRange
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.drawing.copy
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.physics.RectangularMaterial
import processing.core.PVector

internal final class ParticleModel(
  size: Size2D,
  center: Point2D,
  rayResolution: Int,
) : RectangularMaterial(size) {

  public var color: Color = Colors.WHITE
  public var rayColor: Color = Colors.WHITE.copy(alpha = 0.5F)

  public val rays: List<RayModel>
    get() = this._rays

  private val _rays: List<RayModel>

  public val points: List<PointModel>
    get() = this._points

  private var _points = listOf<PointModel>()

  init {
    val step = 360.0F / rayResolution

    this.center = center

    this._rays = FloatRange(0.0F, 360.0F).sequence(step = step).asIterable().map { degree ->
      RayModel(
        direction = PVector.fromAngle(degree * Math.PI.toFloat() / 180),
        position = this.center,
      )
    }
  }

  public fun cast(walls: List<WallModel>) {
    this._points = this._rays.mapNotNull { ray ->
      val points = walls.mapNotNull { ray.cast(it) }
      if (points.isEmpty()) {
        return@mapNotNull null
      }
      val nearest = points.minByOrNull { Point2D.dist(this.center, it) } ?: run {
        return@mapNotNull null
      }
      return@mapNotNull PointModel(position = nearest)
    }
  }

  override fun update() {
    super.update()

    this._rays.forEach { it.position = this.center }
  }
}
