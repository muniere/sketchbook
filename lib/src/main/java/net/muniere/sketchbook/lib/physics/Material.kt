package net.muniere.sketchbook.lib.physics

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D

public abstract class Material {
  public var fillColor: Color? = null
  public var strokeColor: Color? = null

  public var mass: Float = 1.0F
    protected set

  public var center: Point2D = Point2D.zero()
    protected set

  public var velocity: Velocity2D = Velocity2D.zero()
    protected set

  public var acceleration: Acceleration2D = Acceleration2D.zero()
    protected set

  public abstract val top: Float
  public abstract val left: Float
  public abstract val right: Float
  public abstract val bottom: Float

  public abstract fun update()
  public abstract fun apply(force: Force2D)
  public abstract fun moveTo(point: Point2D)
  public abstract fun bounceIn(frame: Rect2D)
}

