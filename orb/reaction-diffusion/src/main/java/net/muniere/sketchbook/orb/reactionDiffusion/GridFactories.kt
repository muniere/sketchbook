package net.muniere.sketchbook.orb.reactionDiffusion

import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.math.Spot

internal object GridFactories {

  internal class Default(
    public val rect: Rect2D,
  ) : GridModel.Factory {

    override fun create(spot: Spot): CellModel {
      val point = Point2D(
        x = spot.column.toFloat(),
        y = spot.row.toFloat(),
      )

      return when (this.rect.contains(point)) {
        true -> CellModel(0.0F, 1.0F)
        false -> CellModel(1.0F, 0.0F)
      }
    }
  }

  internal class Empty : GridModel.Factory {

    override fun create(spot: Spot): CellModel {
      return CellModel(1.0F, 0.0F)
    }
  }
}
