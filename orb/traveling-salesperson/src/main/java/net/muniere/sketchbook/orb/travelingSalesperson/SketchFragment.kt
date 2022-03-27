package net.muniere.sketchbook.orb.travelingSalesperson

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.DrawableRes
import net.muniere.sketchbook.lib.processing.SFragment
import net.muniere.sketchbook.orb.travelingSalesperson.naive.SketchApplet as NaiveSketchApplet
import net.muniere.sketchbook.orb.travelingSalesperson.genetic.SketchApplet as GeneticSketchApplet

public final class SketchFragment : SFragment() {

  //
  // Genome
  //
  private data class Genome(
    public val mode: SketchMode,
  ) {
    private enum class Key {
      MODE,
    }

    public companion object {
      public fun from(bundle: Bundle) = Genome(
        mode = bundle.getString(Key.MODE.name).let(::checkNotNull).let(SketchMode::valueOf)
      )
    }

    public fun toBundle() = Bundle().also {
      it.putString(Key.MODE.name, this.mode.name)
    }
  }

  //
  // Options Menu
  //
  private sealed class OptionsMenuSeed {
    internal abstract val id: Int
    internal abstract val title: CharSequence
    internal abstract val iconRes: Int

    internal class Switch(
      public val isLooping: Boolean,
    ) : OptionsMenuSeed() {
      override val id: Int
        get() = 1

      override val title: CharSequence
        get() = when (this.isLooping) {
          true -> "Pause"
          false -> "Resume"
        }

      override val iconRes: Int
        @DrawableRes
        get() = when (this.isLooping) {
          true -> R.drawable.ic_pause
          false -> R.drawable.ic_play
        }
    }
  }

  private var optionsMenuSeeds = emptyList<OptionsMenuSeed>()

  //
  // Factory
  //
  public companion object {
    public fun newInstance(mode: SketchMode): SketchFragment {
      return SketchFragment().also {
        it.arguments = Genome(mode).toBundle()
      }
    }
  }

  private val genome by lazy {
    Genome.from(this.requireArguments())
  }

  //
  // Lifecycle
  //
  override fun onAttach(context: Context) {
    this.sketch = when (this.genome.mode) {
      SketchMode.NAIVE -> NaiveSketchApplet()
      SketchMode.GENETIC -> GeneticSketchApplet()
    }

    super.onAttach(context)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    this.setHasOptionsMenu(true)
  }

  override fun onPrepareOptionsMenu(menu: Menu) {
    super.onPrepareOptionsMenu(menu)

    menu.clear()

    this.optionsMenuSeeds = listOf(
      OptionsMenuSeed.Switch(
        isLooping = this.requireSketch().getLoopingDeeply()
      )
    )

    this.optionsMenuSeeds.forEach { item ->
      menu.add(Menu.NONE, item.id, Menu.NONE, item.title).also {
        it.setIcon(item.iconRes)
        it.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
      }
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (this.optionsMenuSeeds.firstOrNull { it.id == item.itemId }) {
      is OptionsMenuSeed.Switch -> {
        this.toggleLoop()
        return true
      }
      else -> {
        return super.onOptionsItemSelected(item)
      }
    }
  }

  private fun toggleLoop() {
    val sketch = this.requireSketch()

    when (sketch.isLooping) {
      true -> {
        sketch.noLoop()
        this.post {
          this.requireActivity().invalidateOptionsMenu()
        }
      }
      false -> {
        sketch.loop()
        sketch.doOnNextDraw {
          this.post {
            this.requireActivity().invalidateOptionsMenu()
          }
        }
      }
    }
  }
}
