package net.muniere.sketchbook.orb.imageDithering

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import processing.android.PFragment

public final class SketchFragment : Fragment() {

  private val launcher = this.registerForActivityResult(
    ActivityResultContracts.GetContent()
  ) { result ->
    when (result) {
      null -> this.requireActivity().finish()
      else -> this.launchApplet(result)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return FragmentContainerView(requireContext()).also {
      it.id = View.generateViewId()
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    this.launcher.launch("image/*")
  }

  private fun launchApplet(uri: Uri) {
    val container = this.requireView()
    val fragment = PFragment().also {
      it.sketch = SketchApplet(uri)
    }

    this.childFragmentManager.commit {
      replace(container.id, fragment)
      addToBackStack(null)
    }
  }
}
