package net.muniere.sketchbook

import androidx.fragment.app.Fragment
import net.muniere.sketchbook.orb.bezierCurve.SketchFragment as BezierCurveSketchFragment
import net.muniere.sketchbook.orb.circleMorphing.SketchFragment as CircleMorphingSketchFragment
import net.muniere.sketchbook.orb.circlePacking.SketchFragment as CirclePackingSketchFragment
import net.muniere.sketchbook.orb.fireworks.SketchFragment as FireworksSketchFragment
import net.muniere.sketchbook.orb.imageDithering.SketchFragment as ImageDitheringSketchFragment
import net.muniere.sketchbook.orb.mengerSponge.SketchFragment as MengerSpongeSketchFragment
import net.muniere.sketchbook.orb.purpleRain.SketchFragment as PurpleRainSketchFragment
import net.muniere.sketchbook.orb.starField.SketchFragment as StarFieldSketchFragment
import net.muniere.sketchbook.orb.steeringBehaviors.SketchFragment as SteeringBehaviorsSketchFragment

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

  public abstract fun inflate(): Fragment

  public final object StarField : SketchSeed(
    id = 1,
    title = "Star Field",
    caption = "Star Field",
  ) {
    override fun inflate() = StarFieldSketchFragment()
  }

  public final object MengerSponge : SketchSeed(
    id = 2,
    title = "Menger Sponge",
    caption = "Menger Sponge",
  ) {
    override fun inflate() = MengerSpongeSketchFragment()
  }

  public final object PurpleRain : SketchSeed(
    id = 4,
    title = "Purple Rain",
    caption = "Purple Rain",
  ) {
    override fun inflate() = PurpleRainSketchFragment()
  }

  public final object Fireworks : SketchSeed(
    id = 27,
    title = "Fireworks",
    caption = "Fireworks",
  ) {
    override fun inflate() = FireworksSketchFragment()
  }

  public final object CirclePacking : SketchSeed(
    id = 50,
    title = "Circle Packing",
    caption = "Circle Packing",
  ) {
    override fun inflate() = CirclePackingSketchFragment()
  }

  public final object SteeringBehaviors : SketchSeed(
    id = 59,
    title = "Steering Behaviors",
    caption = "Steering Behaviors",
  ) {
    override fun inflate() = SteeringBehaviorsSketchFragment()
  }

  public final object CircleMorphing : SketchSeed(
    id = 81,
    title = "Circle Morphing",
    caption = "Circle Morphing",
  ) {
    override fun inflate() = CircleMorphingSketchFragment()
  }

  public final object ImageDithering : SketchSeed(
    id = 90,
    title = "Image Dithering",
    caption = "Image Dithering",
  ) {
    override fun inflate() = ImageDitheringSketchFragment()
  }

  public final object BezierCurve : SketchSeed(
    id = 163,
    title = "Bezier Curve",
    caption = "Bezier Curve",
  ) {
    override fun inflate() = BezierCurveSketchFragment()
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
