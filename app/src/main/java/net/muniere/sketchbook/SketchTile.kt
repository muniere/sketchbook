package net.muniere.sketchbook

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.google.android.material.composethemeadapter.MdcTheme

@Composable
public fun SketchTile(
  seed: SketchSeed,
  onClick: (() -> Unit),
) {
  Box(
    Modifier.clickable(onClick = onClick)
  ) {
    Column(
      Modifier.padding(
        horizontal = Dp(16.0F),
        vertical = Dp(12.0F)
      )
    ) {
      Text(
        text = seed.let(SketchFormat::format),
        style = MaterialTheme.typography.bodyLarge,
      )
      Text(
        text = seed.caption,
        style = MaterialTheme.typography.labelMedium,
        color = Color.Gray
      )
    }

    Box(
      Modifier.align(Alignment.BottomCenter)
    ) {
      Divider()
    }
  }
}

@Preview
@Composable
private fun SketchTilePreview() {
  MdcTheme {
    Box(Modifier.background(Color.White)) {
      SketchTile(
        seed = SketchSeed.StarField,
        onClick = { },
      )
    }
  }
}
