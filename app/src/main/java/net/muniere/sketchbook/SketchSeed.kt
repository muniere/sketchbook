package net.muniere.sketchbook

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Api
import androidx.compose.material.icons.outlined.BubbleChart
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Draw
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.outlined.Timeline
import androidx.compose.material.icons.outlined.Umbrella
import androidx.compose.material.icons.outlined.ViewInAr
import androidx.compose.material.icons.outlined.Waves
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.fragment.app.Fragment
import net.muniere.sketchbook.orb.fourierTransform.SketchMode
import net.muniere.sketchbook.orb.bezierCurve.SketchFragment as BezierCurveSketchFragment
import net.muniere.sketchbook.orb.circleMorphing.SketchFragment as CircleMorphingSketchFragment
import net.muniere.sketchbook.orb.circlePacking.SketchFragment as CirclePackingSketchFragment
import net.muniere.sketchbook.orb.fireworks.SketchFragment as FireworksSketchFragment
import net.muniere.sketchbook.orb.fourierSeries.SketchFragment as FourierSeriesSketchFragment
import net.muniere.sketchbook.orb.fourierTransform.SketchFragment as FourierTransformSketchFragment
import net.muniere.sketchbook.orb.imageDithering.SketchFragment as ImageDitheringSketchFragment
import net.muniere.sketchbook.orb.mengerSponge.SketchFragment as MengerSpongeSketchFragment
import net.muniere.sketchbook.orb.nearestNeighbor.SketchFragment as NearestNeighborSketchFragment
import net.muniere.sketchbook.orb.purpleRain.SketchFragment as PurpleRainSketchFragment
import net.muniere.sketchbook.orb.reactionDiffusion.SketchFragment as ReactionDiffusionSketchFragment
import net.muniere.sketchbook.orb.starField.SketchFragment as StarFieldSketchFragment
import net.muniere.sketchbook.orb.steeringBehaviors.SketchFragment as SteeringBehaviorsSketchFragment

public sealed class SketchSeed(
  public val id: String,
  public val icon: ImageVector,
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
    id = "001",
    icon = Icons.Outlined.StarOutline,
    title = "Star Field",
    caption = "Star Field",
  ) {
    override fun inflate() = StarFieldSketchFragment()
  }

  public final object MengerSponge : SketchSeed(
    id = "002",
    icon = Icons.Outlined.ViewInAr,
    title = "Menger Sponge",
    caption = "Menger Sponge",
  ) {
    override fun inflate() = MengerSpongeSketchFragment()
  }

  public final object PurpleRain : SketchSeed(
    id = "004",
    icon = Icons.Outlined.Umbrella,
    title = "Purple Rain",
    caption = "Purple Rain",
  ) {
    override fun inflate() = PurpleRainSketchFragment()
  }

  public final object ReactionDiffusion : SketchSeed(
    id = "013",
    icon = Icons.Outlined.Api,
    title = "Reaction Diffusion",
    caption = "Reaction Diffusion",
  ) {
    override fun inflate() = ReactionDiffusionSketchFragment()
  }

  public final object Fireworks : SketchSeed(
    id = "027",
    icon = Icons.Outlined.LocalFireDepartment,
    title = "Fireworks",
    caption = "Fireworks",
  ) {
    override fun inflate() = FireworksSketchFragment()
  }

  public final object CirclePacking : SketchSeed(
    id = "050",
    icon = Icons.Outlined.BubbleChart,
    title = "Circle Packing",
    caption = "Circle Packing",
  ) {
    override fun inflate() = CirclePackingSketchFragment()
  }

  public final object SteeringBehaviors : SketchSeed(
    id = "059",
    icon = Icons.Outlined.NearMe,
    title = "Steering Behaviors",
    caption = "Steering Behaviors",
  ) {
    override fun inflate() = SteeringBehaviorsSketchFragment()
  }

  public final object NearestNeighbor : SketchSeed(
    id = "070",
    icon = Icons.Outlined.People,
    title = "Nearest Neighbor",
    caption = "Nearest Neighbor",
  ) {
    override fun inflate() = NearestNeighborSketchFragment()
  }

  public final object CircleMorphing : SketchSeed(
    id = "081",
    icon = Icons.Outlined.Circle,
    title = "Circle Morphing",
    caption = "Circle Morphing",
  ) {
    override fun inflate() = CircleMorphingSketchFragment()
  }

  public final object ImageDithering : SketchSeed(
    id = "090",
    icon = Icons.Outlined.Photo,
    title = "Image Dithering",
    caption = "Image Dithering",
  ) {
    override fun inflate() = ImageDitheringSketchFragment()
  }

  public final object FourierSeries : SketchSeed(
    id = "125",
    icon = Icons.Outlined.Waves,
    title = "Fourier Series",
    caption = "Fourier Series",
  ) {
    override fun inflate() = FourierSeriesSketchFragment()
  }

  public final object FourierTransformReal : SketchSeed(
    id = "130-a",
    icon = Icons.Outlined.Draw,
    title = "Fourier Transform",
    caption = "Fourier Transform with Real Numbers",
  ) {
    override fun inflate() = FourierTransformSketchFragment.newInstance(SketchMode.REAL)
  }

  public final object FourierTransformComplex : SketchSeed(
    id = "130-b",
    icon = Icons.Outlined.Draw,
    title = "Fourier Transform",
    caption = "Fourier Transform with Complex Numbers",
  ) {
    override fun inflate() = FourierTransformSketchFragment.newInstance(SketchMode.COMPLEX)
  }

  public final object BezierCurve : SketchSeed(
    id = "163",
    icon = Icons.Outlined.Timeline,
    title = "Bezier Curve",
    caption = "Bezier Curve",
  ) {
    override fun inflate() = BezierCurveSketchFragment()
  }

  override fun equals(other: Any?): Boolean {
    return other is SketchSeed && this.id == other.id
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + title.hashCode()
    result = 31 * result + caption.hashCode()
    return result
  }
}
