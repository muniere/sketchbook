package net.muniere.sketchbook.orb.steeringEvolutionary

import net.muniere.sketchbook.lib.concatinate
import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.graphics.Point2D
import net.muniere.sketchbook.lib.graphics.Rect2D
import net.muniere.sketchbook.lib.processing.FrameClock
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.background

internal class SketchApplet : SApplet() {

  private object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#222222")
    }

    internal object Medicine {
      internal const val RADIUS = 5.0F
      internal const val SCORE = 20.0F
    }

    internal object Poison {
      internal const val RADIUS = 5.0F
      internal const val SCORE = -20.0F
    }

    internal object Stress {
      internal const val SCORE = 0.2F
    }

    internal object Vehicle {
      internal const val COUNT = 5
      internal const val RADIUS = 20.0F
      internal const val SCORE = 100.0F
      internal const val REWARD_WEIGHT = 1.0F
      internal const val PENALTY_WEIGHT = -0.5F
      internal const val REWARD_RADIUS = 100.0F
      internal const val PENALTY_RADIUS = 75.0F
    }

    internal object Default {
      internal const val MEDICINE_COUNT = 50
      internal const val POISON_COUNT = 10
    }

    internal object Feed {
      internal const val CLOCK_SPEED = 1.0F
      internal const val CLOCK_INTERVAL = 60
      internal const val MEDICINE_COUNT = 5
      internal const val POISON_COUNT = 5
    }
  }

  private lateinit var model: ApplicationModel
  private lateinit var widget: ApplicationWidget

  override fun configure() = listOf(
    MetricsPlugin(this.g)
  )

  override fun doSetup() {
    val frame = Rect2D(
      origin = Point2D.zero(),
      size = this.size,
    )

    val materials = concatinate(
      List(Params.Default.MEDICINE_COUNT) {
        MaterialModel(
          radius = Params.Medicine.RADIUS,
          center = frame.random(),
          score = Params.Medicine.SCORE,
        )
      },
      List(Params.Default.POISON_COUNT) {
        MaterialModel(
          radius = Params.Poison.RADIUS,
          center = frame.random(),
          score = Params.Poison.SCORE,
        )
      }
    )

    val vehicles = List(Params.Vehicle.COUNT) {
      VehicleModel(
        radius = Params.Vehicle.RADIUS,
        center = frame.random(),
        genome = VehicleGenome(
          weight = WeightModel(
            Params.Vehicle.REWARD_WEIGHT,
            Params.Vehicle.PENALTY_WEIGHT,
          ),
          sensor = SensorModel(
            Params.Vehicle.REWARD_RADIUS,
            Params.Vehicle.PENALTY_RADIUS,
          ),
        ),
        score = Params.Vehicle.SCORE,
      )
    }

    val provider = MaterialProvider(
      clock = FrameClock(this, Params.Feed.CLOCK_SPEED),
      interval = Params.Feed.CLOCK_INTERVAL,
      factory = fun() = concatinate(
        List(Params.Feed.MEDICINE_COUNT) {
          MaterialModel(
            radius = Params.Medicine.RADIUS,
            center = frame.random(),
            score = Params.Medicine.SCORE,
          )
        },
        List(Params.Feed.POISON_COUNT) {
          MaterialModel(
            radius = Params.Poison.RADIUS,
            center = frame.random(),
            score = Params.Poison.SCORE,
          )
        }
      )
    )

    this.model = ApplicationModel(
      frame = frame,
      materials = materials,
      vehicles = vehicles,
      provider = provider,
      stress = Params.Stress.SCORE,
    )

    this.widget = ApplicationWidget(this.g).also {
      it.model = this.model
    }
  }

  override fun doDraw() {
    // canvas
    this.g.background(Params.Canvas.BACKGROUND_COLOR)

    // widget
    this.widget.draw()

    // update
    if (this.model.hasNext()) {
      this.model.update()
    } else {
      this.noLoop()
    }
  }
}
