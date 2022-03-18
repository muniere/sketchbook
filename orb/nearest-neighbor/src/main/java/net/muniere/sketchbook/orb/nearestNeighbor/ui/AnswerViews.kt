package net.muniere.sketchbook.orb.nearestNeighbor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.muniere.sketchbook.orb.nearestNeighbor.domain.NeighborModel
import net.muniere.sketchbook.orb.nearestNeighbor.domain.RatingModel
import java.text.NumberFormat

private val ratingFormat = NumberFormat.getInstance()
private val similarityFormat = NumberFormat.getPercentInstance()

@Composable
internal fun Heading(
  text: String,
  modifier: Modifier = Modifier,
) {
  Text(
    text = text,
    fontWeight = FontWeight.Bold,
    style = MaterialTheme.typography.h6,
    modifier = modifier,
  )
}

@Composable
internal fun RatingTable(
  rating: RatingModel,
  modifier: Modifier = Modifier,
  fractionDigits: Int = 3,
  verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(16.dp),
) {
  ratingFormat.minimumFractionDigits = fractionDigits
  ratingFormat.maximumFractionDigits = fractionDigits

  Column(
    modifier = modifier,
    verticalArrangement = verticalArrangement,
  ) {
    listOf(
      "Episode I" to rating.I,
      "Episode II" to rating.II,
      "Episode III" to rating.III,
      "Episode IV" to rating.IV,
      "Episode V" to rating.V,
      "Episode VI" to rating.VI,
      "Episode VII" to rating.VII,
      "Rogue One" to rating.rogue1,
      "Holiday Special" to rating.holiday,
    ).forEach { (key, value) ->
      Row {
        Box(Modifier.weight(1.0F)) {
          Text(
            text = key,
            color = Color.Gray,
            style = MaterialTheme.typography.subtitle2,
          )
        }
        Box(Modifier.weight(1.0F)) {
          Text(value.let(ratingFormat::format))
        }
      }
    }
  }
}

@Composable
internal fun NeighborTable(
  neighbors: List<NeighborModel>,
  modifier: Modifier = Modifier,
  fractionDigits: Int = 1,
  verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(16.dp),
) {
  similarityFormat.maximumFractionDigits = fractionDigits
  similarityFormat.minimumFractionDigits = fractionDigits
  Column(
    modifier = modifier,
    verticalArrangement = verticalArrangement,
  ) {
    neighbors.forEach { neighbor ->
      Row {
        Box(Modifier.weight(1.0F)) {
          Text(
            text = neighbor.name,
            style = MaterialTheme.typography.subtitle2,
            color = Color.Gray,
          )
        }
        Box(Modifier.weight(1.0F)) {
          Text(neighbor.similarity.let(similarityFormat::format))
        }
      }
    }
  }
}
