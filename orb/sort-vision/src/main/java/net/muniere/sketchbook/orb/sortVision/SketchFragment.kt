package net.muniere.sketchbook.orb.sortVision

import android.content.Context
import android.os.Bundle
import net.muniere.sketchbook.lib.processing.SFragment

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
    this.sketch = SketchApplet(this.genome.mode)

    super.onAttach(context)
  }
}
