package net.muniere.sketchbook.orb.pathFinding

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.DrawableRes
import net.muniere.sketchbook.lib.processing.SFragment

public final class SketchFragment : SFragment() {
  init {
    this.sketch = SketchApplet().also {
      it.setOnSuccessListener {
        this.postShowSuccessAlert()
      }
      it.setOnFailureListener {
        this.postShowFailureAlert()
      }
    }
  }

  private sealed class OptionsMenuSeed {
    internal abstract val id: Int
    internal abstract val title: CharSequence
    internal abstract val iconRes: Int

    internal object Refresh : OptionsMenuSeed() {
      override val id: Int
        get() = 1

      override val title: CharSequence
        get() = "Refresh"

      override val iconRes: Int
        @DrawableRes
        get() = R.drawable.ic_refresh
    }
  }

  private var optionsMenuSeeds = listOf<OptionsMenuSeed>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    this.setHasOptionsMenu(true)
  }

  override fun onPrepareOptionsMenu(menu: Menu) {
    super.onPrepareOptionsMenu(menu)

    menu.clear()

    this.optionsMenuSeeds = listOf(
      OptionsMenuSeed.Refresh
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
      is OptionsMenuSeed.Refresh -> {
        this.requireSketch<SketchApplet>().reset()
        return true
      }
      else -> {
        return super.onOptionsItemSelected(item)
      }
    }
  }

  private fun postShowSuccessAlert() {
    this.post {
      AlertDialog.Builder(this.requireContext())
        .setTitle("Finished")
        .setMessage("✅ Success")
        .setPositiveButton("OK", null)
        .show()

    }
  }

  private fun postShowFailureAlert() {
    this.post {
      AlertDialog.Builder(this.requireContext())
        .setTitle("Finished")
        .setMessage("❌ Failure")
        .setPositiveButton("OK", null)
        .show()
    }
  }
}
