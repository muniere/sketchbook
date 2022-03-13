package net.muniere.sketchbook

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import processing.android.PFragment

public final class SketchActivity : AppCompatActivity() {

  //
  // Genome
  //
  public final class Genome(
    public val id: Int,
  ) {
    private enum class Keys {
      ID,
    }

    companion object {
      public fun from(intent: Intent) = Genome(
        id = intent.getIntExtra(Keys.ID.name, 0),
      )
    }

    public fun toBundle() = Bundle().also {
      it.putInt(Keys.ID.name, this.id)
    }
  }

  private val genome by lazy {
    Genome.from(this.intent)
  }

  //
  // View
  //
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val seed = SketchSeed.values().first { it.id == this.genome.id }

    val root = FragmentContainerView(this).also { it ->
      it.id = View.generateViewId()
      it.layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT,
      )
    }

    val fragment = PFragment().also {
      it.sketch = seed.inflate()
    }

    this.title = SketchFormat.format(seed)

    this.setContentView(root)

    this.supportFragmentManager
      .beginTransaction()
      .add(root.id, fragment)
      .commit()
  }
}
