package net.muniere.sketchbook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import net.muniere.sketchbook.databinding.MainActivityBinding
import net.muniere.sketchbook.lib.app.viewBinding

public final class MainActivity : AppCompatActivity() {

  private val binding: MainActivityBinding by viewBinding()

  private val controller: MainController by lazy {
    MainController().also {
      it.setOnItemClickListener { view ->
        this.startSketchActivity(checkNotNull(view.seed))
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    this.binding.recyclerView.also {
      it.layoutManager = LinearLayoutManager(this)
      it.adapter = this.controller.adapter
    }

    this.controller.requestModelBuild()
  }

  private fun startSketchActivity(seed: SketchSeed) {
    val intent = Intent(this, SketchActivity::class.java).also {
      it.putExtras(SketchActivity.Genome(seed.id).toBundle())
    }
    this.startActivity(intent)
  }
}
