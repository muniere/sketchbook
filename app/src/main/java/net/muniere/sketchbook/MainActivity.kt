package net.muniere.sketchbook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.muniere.sketchbook.databinding.MainActivityBinding
import net.muniere.sketchbook.lib.app.viewBinding

public final class MainActivity : AppCompatActivity() {

  private val binding: MainActivityBinding by viewBinding()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    this.binding.button.setOnClickListener {
      val genome = SketchActivity.Genome(
        kind = SketchKind.EXAMPLE
      )
      val intent = Intent(this, SketchActivity::class.java).also {
        it.putExtras(genome.toBundle())
      }
      this.startActivity(intent)
    }
  }
}
