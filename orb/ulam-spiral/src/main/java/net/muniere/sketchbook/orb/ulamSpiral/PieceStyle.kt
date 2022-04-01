package net.muniere.sketchbook.orb.ulamSpiral

internal sealed class PieceStyle {

  internal final class Text(
    public val textSize: Float,
  ) : PieceStyle()

  internal final class Circle(
    public val radius: Float,
    public val predicate: (Int) -> Boolean = { true },
  ) : PieceStyle() {
    public fun test(value: Int): Boolean {
      return this.predicate.invoke(value)
    }
  }
}
