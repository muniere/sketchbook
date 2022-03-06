package net.muniere.sketchbook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
import net.muniere.sketchbook.databinding.SketchActivityBinding
import net.muniere.sketchbook.lib.app.viewBinding
import net.muniere.sketchbook.lib.graphics.Size2D
import processing.android.PFragment

public final class SketchActivity : AppCompatActivity() {

  //
  // Genome
  //
  public final class Genome(
    public val kind: SketchKind,
  ) {
    private enum class Keys {
      KIND,
    }

    companion object {
      public fun from(intent: Intent) = Genome(
        kind = intent.getStringExtra(Keys.KIND.name).let(::checkNotNull).let(SketchKind::valueOf)
      )
    }

    public fun toBundle() = Bundle().also {
      it.putString(Keys.KIND.name, this.kind.name)
    }
  }

  private val genome by lazy {
    Genome.from(this.intent)
  }

  //
  // View
  //
  private val binding: SketchActivityBinding by viewBinding()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    this.title = this.genome.kind.seed.let {
      "#${it.id}: ${it.title}"
    }

    this.binding.containerView.doOnLayout { view ->
      val fragment = PFragment().also {
        it.sketch = Sketches.create(
          kind = this.genome.kind,
          size = Size2D(
            width = view.width.toFloat(),
            height = view.height.toFloat(),
          )
        )
      }

      this.supportFragmentManager
        .beginTransaction()
        .add(view.id, fragment)
        .commit()
    }
  }
}
