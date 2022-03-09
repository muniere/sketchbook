package net.muniere.sketchbook.orb.bezierCurve

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.physics.CircularMaterial
import net.muniere.sketchbook.lib.physics.Velocity2D

internal final class VehicleModel(
  radius: Float,
  center: Point2D,
  velocity: Velocity2D,
) : CircularMaterial(
  radius = radius,
  center = center,
  velocity = velocity,
)
