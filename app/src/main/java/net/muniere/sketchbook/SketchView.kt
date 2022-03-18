package net.muniere.sketchbook

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.composethemeadapter.MdcTheme

@Composable
public fun SketchList(seeds: List<SketchSeed>, onItemClick: (seed: SketchSeed) -> Unit) {
  LazyColumn {
    items(seeds, key = SketchSeed::id) { seed ->
      SketchTile(seed) {
        onItemClick.invoke(seed)
      }
    }
  }
}

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
        horizontal = 16.dp,
        vertical = 12.dp
      )
    ) {
      Text(
        text = seed.let(SketchFormat::format),
        style = MaterialTheme.typography.body1,
      )
      Text(
        text = seed.caption,
        style = MaterialTheme.typography.caption,
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
