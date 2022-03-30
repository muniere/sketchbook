package net.muniere.sketchbook.orb.sortVision

import net.muniere.sketchbook.lib.drawing.Colors
import net.muniere.sketchbook.lib.processing.MetricsPlugin
import net.muniere.sketchbook.lib.processing.SApplet
import net.muniere.sketchbook.lib.processing.background
import net.muniere.sketchbook.orb.sortVision.bubble.BubbleSortMachineModel
import net.muniere.sketchbook.orb.sortVision.bubble.BubbleSortMachineWidget
import net.muniere.sketchbook.orb.sortVision.insertion.InsertionSortMachineModel
import net.muniere.sketchbook.orb.sortVision.insertion.InsertionSortMachineWidget
import net.muniere.sketchbook.orb.sortVision.quick.QuickSortMachineModel
import net.muniere.sketchbook.orb.sortVision.quick.QuickSortMachineWidget
import net.muniere.sketchbook.orb.sortVision.selection.SelectionSortMachineModel
import net.muniere.sketchbook.orb.sortVision.selection.SelectionSortMachineWidget

internal class SketchApplet(
  private val mode: SketchMode,
) : SApplet() {
  private object Params {
    internal object Canvas {
      internal val BACKGROUND_COLOR = Colors.parse("#333333")
    }

    internal object Sort {
      internal const val DATA_COUNT = 480
      internal const val CYCLE_SPEED = 20

      internal val DEFAULT_COLOR = Colors.parse("#FFFFFF")
      internal val CURSOR_COLOR = Colors.parse("#FFEA86")
      internal val ANSWER_COLOR = Colors.parse("#AAFFAA")
      internal val FOCUS_COLOR = Colors.parse("#CCFBFF")
      internal val PIVOT_COLOR = Colors.parse("#FFCC93")
      internal val HIGH_COLOR = Colors.parse("#FFBEBE")
      internal val LOW_COLOR = Colors.parse("#CCFBFF")
    }
  }

  private lateinit var model: SortMachineModel
  private lateinit var widget: SortMachineWidget<*>

  override fun configure() = listOf(
    MetricsPlugin(this.g)
  )

  override fun doSetup() {
    val values = IntRange(1, Params.Sort.DATA_COUNT).shuffled()

    when (this.mode) {
      SketchMode.BUBBLE -> {
        val model = BubbleSortMachineModel(values)
        val widget = BubbleSortMachineWidget(this.g).also {
          it.model = model
          it.frame.size = this.size
          it.defaultColor = Params.Sort.DEFAULT_COLOR
          it.cursorColor = Params.Sort.CURSOR_COLOR
          it.answerColor = Params.Sort.ANSWER_COLOR
        }

        this.model = model
        this.widget = widget
      }
      SketchMode.SELECTION -> {
        val model = SelectionSortMachineModel(values)
        val widget = SelectionSortMachineWidget(this.g).also {
          it.model = model
          it.frame.size = this.size
          it.defaultColor = Params.Sort.DEFAULT_COLOR
          it.cursorColor = Params.Sort.CURSOR_COLOR
          it.answerColor = Params.Sort.ANSWER_COLOR
          it.targetColor = Params.Sort.FOCUS_COLOR
        }

        this.model = model
        this.widget = widget
      }
      SketchMode.INSERTION -> {
        val model = InsertionSortMachineModel(values)
        val widget = InsertionSortMachineWidget(this.g).also {
          it.model = model
          it.frame.size = this.size
          it.defaultColor = Params.Sort.DEFAULT_COLOR
          it.cursorColor = Params.Sort.CURSOR_COLOR
          it.answerColor = Params.Sort.ANSWER_COLOR
          it.pivotColor = Params.Sort.PIVOT_COLOR
        }

        this.model = model
        this.widget = widget
      }
      SketchMode.QUICK -> {
        val model = QuickSortMachineModel(values)
        val widget = QuickSortMachineWidget(this.g).also {
          it.model = model
          it.frame.size = this.size
          it.defaultColor = Params.Sort.DEFAULT_COLOR
          it.cursorColor = Params.Sort.CURSOR_COLOR
          it.answerColor = Params.Sort.ANSWER_COLOR
          it.pivotColor = Params.Sort.PIVOT_COLOR
          it.highColor = Params.Sort.HIGH_COLOR
          it.lowColor = Params.Sort.LOW_COLOR
        }

        this.model = model
        this.widget = widget
      }
    }
  }

  override fun doDraw() {
    if (this.model.processState == SortProcessState.FINISHED) {
      this.postNoLoop()
    }

    // canvas
    this.g.background(Params.Canvas.BACKGROUND_COLOR)

    // widget
    this.widget.draw()

    // update
    repeat(Params.Sort.CYCLE_SPEED) {
      this.model.cycle()
    }
  }

  override fun touchEnded() {
    if (this.isLooping) {
      this.noLoop()
    } else {
      this.loop()
    }
  }
}


