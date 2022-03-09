package net.muniere.sketchbook.lib.graphics

public operator fun Point2D.rangeTo(other: Point2D) = Point2DRange(this, other)

public operator fun Point3D.rangeTo(other: Point3D) = Point3DRange(this, other)
