package net.muniere.sketchbook

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

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
