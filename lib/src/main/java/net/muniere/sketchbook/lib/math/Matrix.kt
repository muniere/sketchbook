package net.muniere.sketchbook.lib.math

public final class Matrix<T> private constructor(
  dimen: Dimen,
  values: MutableList<MutableList<T>>,
) {

  public val dimen: Dimen
    get() = this._dimen

  public val width: Int
    get() = this._dimen.width

  public val height: Int
    get() = this._dimen.height

  private val _dimen: Dimen = dimen

  public val values: List<List<T>>
    get() = this._values

  private val _values: MutableList<MutableList<T>> = values

  public companion object {
    public fun <T> create(dimen: Dimen): Matrix<Spot> {
      val spots = MutableList(dimen.height) { row ->
        MutableList(dimen.width) { column ->
          Spot(row, column)
        }
      }

      return Matrix<Spot>(dimen, spots)
    }

    public fun <T> fill(dimen: Dimen, value: T): Matrix<T> {
      val values = MutableList(dimen.height) {
        MutableList(dimen.width) { value }
      }

      return Matrix(dimen, values)
    }

    public fun <T> generate(dimen: Dimen, factory: (Spot) -> T): Matrix<T> {
      val values = MutableList(dimen.height) { row ->
        MutableList(dimen.width) { column ->
          factory.invoke(Spot(row, column))
        }
      }

      return Matrix(dimen, values)
    }
  }

  public fun first(): T {
    return this._values.first().first()
  }

  public fun firstOrNull(): T? {
    return this._values.firstOrNull()?.firstOrNull()
  }

  public fun last(): T {
    return this._values.last().last()
  }

  public fun lastOrNull(): T? {
    return this._values.lastOrNull()?.lastOrNull()
  }

  public fun get(spot: Spot): T {
    return this._values[spot.row][spot.column]
  }

  public fun get(row: Int, column: Int): T {
    return this._values[row][column]
  }

  public fun getOrNull(spot: Spot): T? {
    return this._values.getOrNull(spot.row)?.getOrNull(spot.column)
  }

  public fun getOrNull(row: Int, column: Int): T? {
    return this._values.getOrNull(row)?.getOrNull(column)
  }

  public fun set(spot: Spot, value: T) {
    this._values[spot.row][spot.column] = value
  }

  public fun set(row: Int, column: Int, value: T) {
    this._values[row][column] = value
  }

  public fun forEach(action: (value: T) -> Unit) {
    this._values.forEach { values ->
      values.forEach(action)
    }
  }

  public fun forEachIndexed(action: (value: T, spot: Spot) -> Unit) {
    this._values.forEachIndexed { row, values ->
      values.forEachIndexed { column, value ->
        action(value, Spot(row, column))
      }
    }
  }

  public fun forEachIndexed(action: (value: T, row: Int, column: Int) -> Unit) {
    this._values.forEachIndexed { row, values ->
      values.forEachIndexed { column, value ->
        action(value, row, column)
      }
    }
  }
  public fun forEachSorted(comparator: Comparator<T>, callback: (value: T, spot: Spot) -> Unit) {
    val compare = Comparator<Pair<T, Spot>> { a, b ->
      comparator.compare(a.first, b.first)
    }

    val tuples = this._values.flatMapIndexed { row, values ->
      values.mapIndexed { column, value ->
        Pair(value, Spot(row, column))
      }
    }

    tuples.sortedWith(compare).forEach { (value, spot) ->
      callback(value, spot)
    }
  }

  public fun filterIndexed(predicate: (value: T, spot: Spot) -> Boolean): List<T> {
    return this._values.foldIndexed(listOf<T>()) { row, acc, values ->
      acc + values.filterIndexed { column, value ->
        predicate(value, Spot(row, column))
      }
    }
  }

  public fun <U> mapIndexed(transform: (value: T, spot: Spot) -> U): Matrix<U> {
    val newValues = MutableList(this.dimen.height) { row ->
      MutableList(this.dimen.width) { column ->
        val spot = Spot(row, column)
        val value = this.get(spot)
        transform(value, spot)
      }
    }

    return Matrix(this.dimen, newValues)
  }

  public fun flatten(): List<T> {
    return this._values.flatten()
  }

  override fun equals(other: Any?): Boolean {
    return other is Matrix<*> && this._dimen == other._dimen && this._values == other._values
  }

  override fun hashCode(): Int {
    var result = _dimen.hashCode()
    result = 31 * result + _values.hashCode()
    return result
  }
}
