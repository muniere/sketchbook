package net.muniere.sketchbook.orb.nearestNeighbor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import net.muniere.sketchbook.orb.nearestNeighbor.domain.AnswerModel

@ExperimentalFoundationApi
internal class AnswerFragment : Fragment() {

  private var answer: AnswerModel? = null

  public companion object {
    public fun newInstance(value: AnswerModel): AnswerFragment {
      return AnswerFragment().also {
        it.answer = value
      }
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View = ComposeView(this.requireContext()).also { view ->
    view.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

    view.setContent {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .background(Color.White)
          .clickable(
            interactionSource = MutableInteractionSource(),
            indication = null,
          ) { }
      ) {
        Column(
          modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 12.dp,
          ),
          verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          Heading("Prediction / Rating")

          answer?.prediction?.let {
            RatingTable(it)
          }

          Spacer(Modifier.height(16.dp))

          Heading("Neighbors / Similarity")

          answer?.neighbors?.let {
            NeighborTable(it)
          }
        }
      }
    }
  }
}

