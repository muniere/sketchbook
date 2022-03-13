package net.muniere.sketchbook

import net.muniere.sketchbook.lib.graphics.Size2D
import net.muniere.sketchbook.lib.processing.Sketch
import net.muniere.sketchbook.orb.bezierCurve.Sketch as BezierCurveSketch
import net.muniere.sketchbook.orb.circleMorphing.Sketch as CircleMorphingSketch
import net.muniere.sketchbook.orb.circlePacking.Sketch as CirclePackingSketch
import net.muniere.sketchbook.orb.fireworks.Sketch as FireworksSketch
import net.muniere.sketchbook.orb.imageDithering.Sketch as ImageDitheringSketch
import net.muniere.sketchbook.orb.mengerSponge.Sketch as MengerSpongeSketch
import net.muniere.sketchbook.orb.purpleRain.Sketch as PurpleRainSketch
import net.muniere.sketchbook.orb.starField.Sketch as StarFieldSketch
import net.muniere.sketchbook.orb.steeringBehaviors.Sketch as SteeringBehaviorsSketch

public sealed class SketchSeed(
  public val id: Int,
  public val title: String,
  public val caption: String,
) {

  public companion object {
    public fun values(): List<SketchSeed> {
      return SketchSeed::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .sortedBy { it.id }
    }
  }

  public abstract fun inflate(size: Size2D): Sketch

  public final object StarField : SketchSeed(
    id = 1,
    title = "Star Field",
    caption = "Star Field",
  ) {
    override fun inflate(size: Size2D) = StarFieldSketch(size)
  }

  public final object MengerSponge : SketchSeed(
    id = 2,
    title = "Menger Sponge",
    caption = "Menger Sponge",
  ) {
    override fun inflate(size: Size2D) = MengerSpongeSketch(size)
  }

  public final object PurpleRain : SketchSeed(
    id = 4,
    title = "Purple Rain",
    caption = "Purple Rain",
  ) {
    override fun inflate(size: Size2D) = PurpleRainSketch(size)
  }

  public final object Fireworks : SketchSeed(
    id = 27,
    title = "Fireworks",
    caption = "Fireworks",
  ) {
    override fun inflate(size: Size2D) = FireworksSketch(size)
  }

  public final object CirclePacking : SketchSeed(
    id = 50,
    title = "Circle Packing",
    caption = "Circle Packing",
  ) {
    override fun inflate(size: Size2D) = CirclePackingSketch(size)
  }

  public final object SteeringBehaviors : SketchSeed(
    id = 59,
    title = "Steering Behaviors",
    caption = "Steering Behaviors",
  ) {
    override fun inflate(size: Size2D) = SteeringBehaviorsSketch(size)
  }

  public final object CircleMorphing : SketchSeed(
    id = 81,
    title = "Circle Morphing",
    caption = "Circle Morphing",
  ) {
    override fun inflate(size: Size2D) = CircleMorphingSketch(size)
  }

  public final object ImageDithering : SketchSeed(
    id = 90,
    title = "Image Dithering",
    caption = "Image Dithering",
  ) {
    override fun inflate(size: Size2D) = ImageDitheringSketch(size)
  }

  public final object BezierCurve : SketchSeed(
    id = 163,
    title = "Bezier Curve",
    caption = "Bezier Curve",
  ) {
    override fun inflate(size: Size2D) = BezierCurveSketch(size)
  }

  override fun equals(other: Any?): Boolean {
    return other is SketchSeed && this.id == other.id
  }

  override fun hashCode(): Int {
    var result = id
    result = 31 * result + title.hashCode()
    result = 31 * result + caption.hashCode()
    return result
  }
}
