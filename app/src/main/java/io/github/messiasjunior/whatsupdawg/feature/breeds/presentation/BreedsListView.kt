package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.messiasjunior.whatsupdawg.domain.Breed

@Composable
fun BreedsListView(breeds: List<Breed>, onBreedClicked: (Breed) -> Unit) {
    Column {
        LazyColumn {
            items(breeds) {
                Text(text = it.name)
            }
        }
    }
}
