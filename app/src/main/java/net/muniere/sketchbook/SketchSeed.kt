package net.muniere.sketchbook

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Api
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.BubbleChart
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Draw
import androidx.compose.material.icons.outlined.Gesture
import androidx.compose.material.icons.outlined.Gradient
import androidx.compose.material.icons.outlined.LensBlur
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.Pattern
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material.icons.outlined.Schema
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Sensors
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.outlined.Storm
import androidx.compose.material.icons.outlined.Timeline
import androidx.compose.material.icons.outlined.Umbrella
import androidx.compose.material.icons.outlined.ViewInAr
import androidx.compose.material.icons.outlined.Waves
import androidx.compose.material.icons.outlined.Window
import androidx.compose.material.icons.outlined.ZoomOutMap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.fragment.app.Fragment
import net.muniere.sketchbook.orb.bezierCurve.SketchFragment as BezierCurveSketchFragment
import net.muniere.sketchbook.orb.circleMorphing.SketchFragment as CircleMorphingSketchFragment
import net.muniere.sketchbook.orb.circlePacking.SketchFragment as CirclePackingSketchFragment
import net.muniere.sketchbook.orb.fireworks.SketchFragment as FireworksSketchFragment
import net.muniere.sketchbook.orb.fourierSeries.SketchFragment as FourierSeriesSketchFragment
import net.muniere.sketchbook.orb.fourierTransform.SketchFragment as FourierTransformSketchFragment
import net.muniere.sketchbook.orb.fourierTransform.SketchMode as FourierTransformSketchMode
import net.muniere.sketchbook.orb.imageDithering.SketchFragment as ImageDitheringSketchFragment
import net.muniere.sketchbook.orb.imageMosaic.SketchFragment as ImageMosaicSketchFragment
import net.muniere.sketchbook.orb.langtonAnt.SketchFragment as LangtonAntSketchFragment
import net.muniere.sketchbook.orb.lifeGame.SketchFragment as LifeGameSketchFragment
import net.muniere.sketchbook.orb.mengerSponge.SketchFragment as MengerSpongeSketchFragment
import net.muniere.sketchbook.orb.mitosisSimulation.SketchFragment as MitosisSimulationSketchFragment
import net.muniere.sketchbook.orb.nearestNeighbor.SketchFragment as NearestNeighborSketchFragment
import net.muniere.sketchbook.orb.pathFinding.SketchFragment as PathFindingSketchFragment
import net.muniere.sketchbook.orb.purpleRain.SketchFragment as PurpleRainSketchFragment
import net.muniere.sketchbook.orb.quadtree.SketchFragment as QuadtreeSketchFragment
import net.muniere.sketchbook.orb.raycasting.SketchFragment as RaycastingSketchFragment
import net.muniere.sketchbook.orb.reactionDiffusion.SketchFragment as ReactionDiffusionSketchFragment
import net.muniere.sketchbook.orb.sortVision.SketchFragment as SortVisionSketchFragment
import net.muniere.sketchbook.orb.sortVision.SketchMode as SortVisionSketchMode
import net.muniere.sketchbook.orb.starField.SketchFragment as StarFieldSketchFragment
import net.muniere.sketchbook.orb.steeringBehaviors.SketchFragment as SteeringBehaviorsSketchFragment
import net.muniere.sketchbook.orb.steeringEvolutionary.SketchFragment as SteeringEvolutionarySketchFragment
import net.muniere.sketchbook.orb.travelingSalesperson.SketchFragment as TravelingSalespersonSketchFragment
import net.muniere.sketchbook.orb.travelingSalesperson.SketchMode as TravelingSalespersonSketchMode
import net.muniere.sketchbook.orb.ulamSpiral.SketchFragment as UlamSpiralSketchFragment

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

  public final object MitosisSimulation : SketchSeed(
    id = "006",
    icon = Icons.Outlined.Gradient,
    title = "Mitosis Simulation",
    caption = "Mitosis Simulation",
  ) {
    override fun inflate() = MitosisSimulationSketchFragment()
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

  public final object TravelingSalespersonBruteForce : SketchSeed(
    id = "035-a",
    icon = Icons.Outlined.Gesture,
    title = "Traveling Salesperson",
    caption = "Traveling Salesperson with Naive Brute Force",
  ) {
    override fun inflate() = TravelingSalespersonSketchFragment.newInstance(TravelingSalespersonSketchMode.NAIVE)
  }

  public final object TravelingSalespersonGenetic : SketchSeed(
    id = "035-b",
    icon = Icons.Outlined.Gesture,
    title = "Traveling Salesperson",
    caption = "Traveling Salesperson with Genetic Algorithm",
  ) {
    override fun inflate() = TravelingSalespersonSketchFragment.newInstance(TravelingSalespersonSketchMode.GENETIC)
  }

  public final object ImageMosaic : SketchSeed(
    id = "049",
    icon = Icons.Outlined.LensBlur,
    title = "Image Mosaic",
    caption = "Image Mosaic",
  ) {
    override fun inflate() = ImageMosaicSketchFragment()
  }

  public final object CirclePacking : SketchSeed(
    id = "050",
    icon = Icons.Outlined.BubbleChart,
    title = "Circle Packing",
    caption = "Circle Packing",
  ) {
    override fun inflate() = CirclePackingSketchFragment()
  }

  public final object PathFinding : SketchSeed(
    id = "051",
    icon = Icons.Outlined.Search,
    title = "Path Finding",
    caption = "Path Finding with A* algorithm",
  ) {
    override fun inflate() = PathFindingSketchFragment()
  }

  public final object SteeringBehaviors : SketchSeed(
    id = "059",
    icon = Icons.Outlined.ZoomOutMap,
    title = "Steering Behaviors",
    caption = "Steering Behaviors",
  ) {
    override fun inflate() = SteeringBehaviorsSketchFragment()
  }

  public final object SteeringEvolutionary : SketchSeed(
    id = "069",
    icon = Icons.Outlined.NearMe,
    title = "Steering Evolutionary",
    caption = "Steering Behaviors with Evolutionary",
  ) {
    override fun inflate() = SteeringEvolutionarySketchFragment()
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

  public final object LifeGame : SketchSeed(
    id = "085",
    icon = Icons.Outlined.Schema,
    title = "Life Game",
    caption = "Conway's Game of Life",
  ) {
    override fun inflate() = LifeGameSketchFragment()
  }

  public final object LangtonAnt : SketchSeed(
    id = "089",
    icon = Icons.Outlined.Pattern,
    title = "Langton's Ant",
    caption = "Langton's Ant",
  ) {
    override fun inflate() = LangtonAntSketchFragment()
  }

  public final object ImageDithering : SketchSeed(
    id = "090",
    icon = Icons.Outlined.Photo,
    title = "Image Dithering",
    caption = "Image Dithering",
  ) {
    override fun inflate() = ImageDitheringSketchFragment()
  }

  public final object Quadtree : SketchSeed(
    id = "098",
    icon = Icons.Outlined.Window,
    title = "Quadtree",
    caption = "Collision Detection by Quadtree",
  ) {
    override fun inflate() = QuadtreeSketchFragment()
  }

  public final object SortVisionBubble : SketchSeed(
    id = "114-a",
    icon = Icons.Outlined.BarChart,
    title = "Sort Visualization",
    caption = "Sort Visualization / Bubble Sort",
  ) {
    override fun inflate() = SortVisionSketchFragment.newInstance(SortVisionSketchMode.BUBBLE)
  }

  public final object SortVisionSelection : SketchSeed(
    id = "114-b",
    icon = Icons.Outlined.BarChart,
    title = "Sort Visualization",
    caption = "Sort Visualization / Selection Sort",
  ) {
    override fun inflate() = SortVisionSketchFragment.newInstance(SortVisionSketchMode.SELECTION)
  }

  public final object SortVisionInsertion : SketchSeed(
    id = "114-c",
    icon = Icons.Outlined.BarChart,
    title = "Sort Visualization",
    caption = "Sort Visualization / Insertion Sort",
  ) {
    override fun inflate() = SortVisionSketchFragment.newInstance(SortVisionSketchMode.INSERTION)
  }

  public final object SortVisionQuick : SketchSeed(
    id = "114-d",
    icon = Icons.Outlined.BarChart,
    title = "Sort Visualization",
    caption = "Sort Visualization / Quick Sort",
  ) {
    override fun inflate() = SortVisionSketchFragment.newInstance(SortVisionSketchMode.QUICK)
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
    override fun inflate() = FourierTransformSketchFragment.newInstance(FourierTransformSketchMode.REAL)
  }

  public final object FourierTransformComplex : SketchSeed(
    id = "130-b",
    icon = Icons.Outlined.Draw,
    title = "Fourier Transform",
    caption = "Fourier Transform with Complex Numbers",
  ) {
    override fun inflate() = FourierTransformSketchFragment.newInstance(FourierTransformSketchMode.COMPLEX)
  }

  public final object RayCasting : SketchSeed(
    id = "146",
    icon = Icons.Outlined.Sensors,
    title = "Ray Casting",
    caption = "Ray Casting",
  ) {
    override fun inflate() = RaycastingSketchFragment()
  }

  public final object BezierCurve : SketchSeed(
    id = "163",
    icon = Icons.Outlined.Timeline,
    title = "Bezier Curve",
    caption = "Bezier Curve",
  ) {
    override fun inflate() = BezierCurveSketchFragment()
  }

  public final object UlamSpiral : SketchSeed(
    id = "167",
    icon = Icons.Outlined.Storm,
    title = "Ulam Spiral",
    caption = "Ulam Spiral",
  ) {
    override fun inflate() = UlamSpiralSketchFragment()
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
