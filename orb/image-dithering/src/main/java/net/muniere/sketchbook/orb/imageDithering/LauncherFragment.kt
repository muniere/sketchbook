package net.muniere.sketchbook.orb.imageDithering

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

internal final class LauncherFragment : Fragment() {

  public fun interface OnResultListener<T> {
    public fun onResult(value: T)
  }

  public fun setOnResultListener(listener: OnResultListener<Uri>?) {
    this.onResultListener = listener
  }

  private var onResultListener: OnResultListener<Uri>? = null

  public fun interface OnCancelListener {
    public fun onCancel()
  }

  public fun setOnCancelListener(listener: OnCancelListener?) {
    this.onCancelListener = listener
  }

  private var onCancelListener: OnCancelListener? = null

  private val launcher = this.registerForActivityResult(
    ActivityResultContracts.GetContent()
  ) { result ->
    when (result) {
      null -> this.onCancelListener?.onCancel()
      else -> this.onResultListener?.onResult(result)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return ComposeView(this.requireContext()).also {
      it.setContent {
        Box(
          contentAlignment = Alignment.Center,
        ) {
          FilledTonalButton(
            onClick = { launcher.launch("image/*") }
          ) {
            Text("Choose")
          }
        }
      }
    }
  }
}
