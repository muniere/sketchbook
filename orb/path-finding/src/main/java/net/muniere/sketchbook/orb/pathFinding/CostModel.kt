package net.muniere.sketchbook.orb.pathFinding

internal final data class CostModel(
  public var g: Float,
  public var h: Float,
) {

  public val f: Float
    get() = this.g + this.h

  companion object {
    public fun zero() = CostModel(0.0F, 0.0F)
  }
}
