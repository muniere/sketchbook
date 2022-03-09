package net.muniere.sketchbook.orb.circleMorphing

import net.muniere.sketchbook.lib.graphics.rangeTo

internal interface MorphingModel {
  public val progress: Float
  public fun forward()
  public fun backward()
  public fun path(): PathModel
}

internal class InterpolationMorphingModel(
  private val src: PathModel,
  private val dst: PathModel,
  private val interpolator: InterpolatorModel,
) : MorphingModel {

  constructor(src: PathModel, dst: PathModel) : this(src, dst, DefaultInterpolatorModel())

  override var progress: Float = 0.0F
    private set

  override fun forward() {
    this.forward(0.005F)
  }

  public fun forward(delta: Float) {
    this.progress = (this.progress + delta).coerceAtMost(1.0F)
  }

  override fun backward() {
    this.backward(0.005F)
  }

  public fun backward(delta: Float) {
    this.progress = (this.progress - delta).coerceAtLeast(0.0F)
  }

  override fun path(): PathModel {
    val amount = this.interpolator.compute(this.progress)

    val points = this.src.points.zip(this.dst.points).map { (src, dst) ->
      (src..dst).lerp(amount)
    }

    return PathModel(points)
  }
}

internal class RandomSwapMorphingModel(
  private val src: PathModel,
  private val dst: PathModel,
) : MorphingModel {
  private val indices = mutableSetOf<Int>()

  override val progress: Float
    get() = this.indices.size.toFloat() / this.src.length

  override fun forward() {
    this.indices.add(this.src.points.indices.subtract(this.indices).random())
  }

  override fun backward() {
    this.indices.remove(this.indices.random())
  }

  override fun path(): PathModel {
    val points = this.src.points.zip(this.dst.points).mapIndexed { i, (src, dst) ->
      if (this.indices.contains(i)) dst else src
    }

    return PathModel(points)
  }
}
