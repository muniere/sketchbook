package net.muniere.sketchbook.orb.pathFinding

import net.muniere.sketchbook.lib.processing.ModelWidget
import processing.core.PGraphics

internal class SolverWidget(graphics: PGraphics) : ModelWidget<SolverModel>(graphics) {
  public val palette = GraphPalette()

  private val node = NodeWidget(graphics)

  override fun doDraw(model: SolverModel) {
    val paint = SolverPaint(
      solver = model,
      palette = this.palette,
    )

    model.graph.walk { node ->
      this.node.model = node
      this.node.color = paint.selectColor(node)
      this.node.draw()
    }
  }
}
