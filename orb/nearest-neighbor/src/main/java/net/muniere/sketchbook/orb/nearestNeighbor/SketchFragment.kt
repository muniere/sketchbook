package net.muniere.sketchbook.orb.nearestNeighbor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import net.muniere.sketchbook.orb.nearestNeighbor.domain.AnswerModel

public final class SketchFragment : Fragment() {

  private val self: SketchFragment
    get() = this

  override fun onAttach(context: Context) {
    super.onAttach(context)

    val callback = this.requireActivity().onBackPressedDispatcher.addCallback {
      if (self.childFragmentManager.backStackEntryCount > 0) {
        self.childFragmentManager.popBackStack()
      }
    }.also {
      it.isEnabled = this.childFragmentManager.backStackEntryCount > 0
    }

    this.childFragmentManager.addFragmentOnAttachListener { _, fragment ->
      this.onAttachChildFragment(fragment)
    }
    this.childFragmentManager.addOnBackStackChangedListener {
      callback.isEnabled = this.childFragmentManager.backStackEntryCount > 0
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return FragmentContainerView(this.requireContext()).also {
      it.id = View.generateViewId()
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    this.launchForm()
  }

  private fun onAttachChildFragment(fragment: Fragment) {
    when (fragment) {
      is FormFragment -> {
        fragment.setOnResultListener {
          this.launchAnswer(it)
        }
      }
      is AnswerFragment -> {
        /* no-op */
      }
    }
  }

  private fun launchForm() {
    this.childFragmentManager.commit {
      replace(self.requireView().id, FormFragment())
    }
  }

  private fun launchAnswer(answer: AnswerModel) {
    this.childFragmentManager.commit {
      add(self.requireView().id, AnswerFragment.newInstance(answer))
      addToBackStack(null)
    }
  }
}

