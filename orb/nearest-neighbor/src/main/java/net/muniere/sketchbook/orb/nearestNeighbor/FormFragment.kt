package net.muniere.sketchbook.orb.nearestNeighbor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.muniere.sketchbook.orb.nearestNeighbor.domain.AnswerModel
import net.muniere.sketchbook.orb.nearestNeighbor.domain.SolverModel
import net.muniere.sketchbook.orb.nearestNeighbor.io.DataLoader
import net.muniere.sketchbook.orb.nearestNeighbor.ui.ValueInputField
import net.muniere.sketchbook.orb.nearestNeighbor.ui.update

internal final class FormFragment : Fragment() {

  //
  // Listener
  //
  internal fun interface OnResultListener {
    public fun onResult(result: AnswerModel)
  }

  internal fun setOnResultListener(listener: OnResultListener?) {
    this.onResultListener = listener
  }

  private var onResultListener: OnResultListener? = null

  //
  // State
  //
  private val state = MutableStateFlow(FormState())
  private val running = MutableStateFlow(false)

  //
  // Model
  //
  private lateinit var solver: SolverModel

  //
  // Factory
  //
  public companion object {
    public fun newInstance(): FormFragment {
      return FormFragment()
    }
  }

  private val self: FormFragment
    get() = this

  //
  // Lifecycle
  //
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View = ComposeView(this.requireContext()).also { view ->
    view.setViewCompositionStrategy(
      ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
    )

    view.setContent {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(Color.White),
        contentAlignment = Alignment.Center,
      ) {
        val running = self.running.collectAsState()

        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
          verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
          val state = self.state.collectAsState()

          Spacer(
            Modifier.height(12.dp)
          )

          ValueInputField(
            title = "Episode I",
            value = state.value.I,
            onChange = { value -> self.state.update { it.copy(I = value) } }
          )
          ValueInputField(
            title = "Episode II",
            value = state.value.II,
            onChange = { value -> self.state.update { it.copy(II = value) } }
          )
          ValueInputField(
            title = "Episode III",
            value = state.value.III,
            onChange = { value -> self.state.update { it.copy(III = value) } }
          )
          ValueInputField(
            title = "Episode IV",
            value = state.value.IV,
            onChange = { value -> self.state.update { it.copy(IV = value) } }
          )
          ValueInputField(
            title = "Episode V",
            value = state.value.V,
            onChange = { value -> self.state.update { it.copy(V = value) } }
          )
          ValueInputField(
            title = "Episode VI",
            value = state.value.VI,
            onChange = { value -> self.state.update { it.copy(VI = value) } }
          )
          ValueInputField(
            title = "Episode VII",
            value = state.value.VII,
            onChange = { value -> self.state.update { it.copy(VII = value) } }
          )
          ValueInputField(
            title = "Rogue One",
            value = state.value.rogue1,
            onChange = { value -> self.state.update { it.copy(rogue1 = value) } }
          )
          ValueInputField(
            title = "Holiday Special",
            value = state.value.holiday,
            onChange = { value -> self.state.update { it.copy(holiday = value) } }
          )

          Box(
            Modifier.align(Alignment.End)
          ) {
            Button(onClick = { self.submit(state.value) }) {
              Text("Submit")
            }
          }

          Spacer(
            Modifier.height(12.dp)
          )
        }

        if (running.value) {
          CircularProgressIndicator()
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    this.lifecycleScope.launch(Dispatchers.IO) {
      val records = DataLoader(self.resources.assets).load()

      withContext(Dispatchers.Default) {
        val entities = records.map(DataConversions::decode)

        self.solver = SolverModel(entities)
      }
    }
  }

  //
  // Action
  //
  private fun submit(state: FormState) {
    this.running.value = true

    this.lifecycleScope.launch(Dispatchers.IO) {
      val timeout = launch { delay(1500L) }

      val persona = state.toPerson()
      val answer = self.solver.solve(persona)

      timeout.join()

      withContext(Dispatchers.Main) {
        self.onResultListener?.onResult(answer)
        self.running.value = false
      }
    }
  }
}
