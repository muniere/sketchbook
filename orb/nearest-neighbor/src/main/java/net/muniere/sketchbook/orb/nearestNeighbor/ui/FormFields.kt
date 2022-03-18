package net.muniere.sketchbook.orb.nearestNeighbor.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun ValueInputField(
  title: String,
  value: Int?,
  options: List<Int?> = listOf(null, 1, 2, 3, 4, 5),
  onChange: (Int?) -> Unit,
) {
  var expaned by remember { mutableStateOf(false) }

  Column(
    Modifier.fillMaxWidth()
  ) {
    Text(
      text = title,
      fontWeight = FontWeight.Bold,
    )

    Spacer(
      Modifier.height(8.dp)
    )

    ExposedDropdownMenuBox(
      expanded = expaned,
      onExpandedChange = { expaned = !expaned },
      modifier = Modifier.fillMaxWidth()
    ) {
      TextField(
        readOnly = true,
        value = value.toString(),
        onValueChange = { },
        trailingIcon = {
          ExposedDropdownMenuDefaults.TrailingIcon(expanded = expaned)
        },
        colors = ExposedDropdownMenuDefaults.textFieldColors(),
        modifier = Modifier.fillMaxWidth(),
      )

      ExposedDropdownMenu(
        expanded = expaned,
        onDismissRequest = { expaned = false }
      ) {
        options.forEach { value ->
          DropdownMenuItem(
            modifier = Modifier.clickable {
              onChange(value)
              expaned = false
            },
            onClick = {
              onChange(value)
              expaned = false
            }
          ) {
            Text(text = value.toString())
          }
        }
      }
    }
  }
}
