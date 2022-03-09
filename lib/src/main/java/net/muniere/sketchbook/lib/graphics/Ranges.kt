package net.muniere.sketchbook.lib.graphics

public operator fun Point2D.rangeTo(other: Point2D) = PointRange2D(this, other)

public operator fun Point3D.rangeTo(other: Point3D) = PointRange3D(this, other)
