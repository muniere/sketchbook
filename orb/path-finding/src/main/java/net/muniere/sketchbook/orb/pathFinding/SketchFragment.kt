package net.muniere.sketchbook.orb.pathFinding

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import net.muniere.sketchbook.lib.processing.SFragment

public final class SketchFragment : SFragment() {
  init {
    this.sketch = SketchApplet().also {
      it.setOnSuccessListener {
        Handler(Looper.getMainLooper()).post {
          AlertDialog.Builder(this.requireContext())
            .setTitle("Finished")
            .setMessage("✅ Success")
            .setPositiveButton("OK", null)
            .show()
        }
      }
      it.setOnFailureListener {
        Handler(Looper.getMainLooper()).post {
          AlertDialog.Builder(this.requireContext())
            .setTitle("Finished")
            .setMessage("❌ Failure")
            .setPositiveButton("OK", null)
            .show()
        }
      }
    }
  }

  private sealed class OptionsMenuItem {
    internal abstract val id: Int
    internal abstract val title: CharSequence
    internal abstract val iconRes: Int

    internal object Refresh : OptionsMenuItem() {
      override val id: Int
        get() = 1

      override val title: CharSequence
        get() = "Refresh"

      override val iconRes: Int
        get() = R.drawable.ic_refresh
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    this.setHasOptionsMenu(true)
  }

  override fun onPrepareOptionsMenu(menu: Menu) {
    super.onPrepareOptionsMenu(menu)

    menu.clear()

    listOf(
      OptionsMenuItem.Refresh
    ).forEach { item ->
      menu.add(Menu.NONE, item.id, Menu.NONE, item.title).also {
        it.setIcon(item.iconRes)
        it.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
      }
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      OptionsMenuItem.Refresh.id -> {
        this.requireSketch<SketchApplet>().reset()
        return true
      }
      else -> {
        return super.onOptionsItemSelected(item)
      }
    }
  }
}
