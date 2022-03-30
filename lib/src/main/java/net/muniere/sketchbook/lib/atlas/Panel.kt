package net.muniere.sketchbook.lib.atlas

import net.muniere.sketchbook.lib.math.Dimen
import net.muniere.sketchbook.lib.math.Matrix
import net.muniere.sketchbook.lib.math.Spot

public final class Panel<T>(
  private val values: Matrix<T>,
) {

  public val dimen: Dimen
    get() = this.values.dimen

  public val width: Int
    get() = this.values.width

  public val height: Int
    get() = this.values.height

  public companion object {
    public fun <T> fill(dimen: Dimen, value: T): Panel<T> = Panel(
      values = Matrix.fill(dimen, value)
    )

    public fun <T> generate(dimen: Dimen, factory: (Spot) -> T): Panel<T> = Panel(
      values = Matrix.generate(dimen, factory)
    )
  }

  public fun first(): T = this.values.first()
  public fun firstOrNull(): T? = this.values.firstOrNull()
  public fun last(): T = this.values.last()
  public fun lastOrNull(): T? = this.values.lastOrNull()
  public fun get(spot: Spot): T = this.values.get(spot)
  public fun get(row: Int, column: Int): T = this.values.get(row, column)
  public fun getOrNull(spot: Spot): T? = this.values.getOrNull(spot)
  public fun getOrNull(row: Int, column: Int): T? = this.values.getOrNull(row, column)
  public fun set(spot: Spot, value: T) = this.values.set(spot, value)
  public fun set(row: Int, column: Int, value: T) = this.values.set(row, column, value)
  public fun forEach(action: (value: T) -> Unit) = this.values.forEach(action)
  public fun forEachIndexed(action: (value: T, spot: Spot) -> Unit) = this.values.forEachIndexed(action)
  public fun forEachIndexed(action: (value: T, row: Int, column: Int) -> Unit) = this.values.forEachIndexed(action)
  public fun filterIndexed(predicate: (value: T, spot: Spot) -> Boolean): List<T> = this.values.filterIndexed(predicate)
  public fun <U> mapIndexed(transform: (value: T, spot: Spot) -> U): Panel<U> = this.values.mapIndexed(transform).let(::Panel)
  public fun flatten(): List<T> = this.values.flatten()
  override fun equals(other: Any?): Boolean = other is Panel<*> && this.values == other.values
  override fun hashCode(): Int = this.values.hashCode()
}
