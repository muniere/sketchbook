package net.muniere.sketchbook.orb.imageDithering

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import processing.android.PFragment

public final class SketchFragment : Fragment() {

  override fun onAttach(context: Context) {
    super.onAttach(context)

    val callback = this.requireActivity().onBackPressedDispatcher.addCallback(this) {
      childFragmentManager.popBackStack()
    }

    callback.isEnabled = false

    this.childFragmentManager.addOnBackStackChangedListener {
      callback.isEnabled = this.childFragmentManager.backStackEntryCount > 0
    }

    this.childFragmentManager.addFragmentOnAttachListener { _, fragment ->
      when (fragment) {
        is LauncherFragment -> {
          fragment.setOnResultListener { uri ->
            this.launchApplet(uri)
          }
        }
        is PFragment -> {
          /* no-op */
        }
      }
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return FragmentContainerView(requireContext()).also {
      it.id = View.generateViewId()
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    this.childFragmentManager.commit {
      add(view.id, LauncherFragment())
    }
  }

  private fun launchApplet(uri: Uri) {
    val container = this.requireView()
    val fragment = PFragment().also {
      it.sketch = SketchApplet(uri)
    }

    this.childFragmentManager.commit {
      setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit)
      add(container.id, fragment)
      addToBackStack(null)
    }
  }
}
