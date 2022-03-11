package net.muniere.sketchbook

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Scaffold
import com.google.android.material.composethemeadapter.MdcTheme

public final class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    this.setContent {
      MdcTheme {
        Scaffold {
          SketchList(SketchSeed.values()) { seed ->
            val intent = Intent(this, SketchActivity::class.java).also {
              it.putExtras(SketchActivity.Genome(seed.id).toBundle())
            }
            this.startActivity(intent)
          }
        }
      }
    }
  }
}
