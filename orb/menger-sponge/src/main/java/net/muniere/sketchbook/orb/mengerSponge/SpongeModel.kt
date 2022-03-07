package net.muniere.sketchbook.orb.mengerSponge

import android.graphics.Color
import net.muniere.sketchbook.lib.graphics.Point3D

public final class SpongeModel(
  size: Float,
) {
  public var strokeColor = Color.valueOf(0xFFFFFF)

  public var fillColor: Color
    get() = this.cubes.first().color
    set(value) = this.cubes.forEach { it.color = value }

  public var cubes: List<CubeModel>
    private set

  public var rotation: Float = 0.0F

  init {
    val seed = CubeModel(
      size = size,
      center = Point3D.zero(),
    )

    this.cubes = listOf(seed)
  }

  public fun rotate(value: Float) {
    this.rotation += value
  }

  public fun cycle() {
    this.cubes = this.cubes.flatMap { it.spawn() }
  }
}
