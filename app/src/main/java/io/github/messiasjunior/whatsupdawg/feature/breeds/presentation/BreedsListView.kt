package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.messiasjunior.whatsupdawg.core.ui.theme.Spacing
import io.github.messiasjunior.whatsupdawg.core.ui.theme.WhatsUpDawgTheme

@Composable
fun BreedsListView(breeds: List<BreedUiModel>, onBreedClick: (BreedUiModel) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = Spacing.VerySmall)) {
        LazyVerticalGrid(columns = GridCells.Adaptive(192.dp)) {
            items(breeds) { breed ->
                Spacer(modifier = Modifier.height(Spacing.Small))
                BreedCard(breed = breed, onClick = { onBreedClick(breed) })
            }
        }
    }
}

@Composable
private fun BreedCard(breed: BreedUiModel, onClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .padding(all = Spacing.VerySmall)
            .fillMaxWidth()
            .clickable { onClick() }
            .semantics(mergeDescendants = true) {
                contentDescription = breed.name
            },
    ) {
        Column {
            breed.imageUrl?.let {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(192.dp)
                        .clip(CardDefaults.outlinedShape),
                    model = breed.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                modifier = Modifier.padding(all = Spacing.Small),
                text = breed.name,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


@Composable
@Preview
private fun BreedCardPreview() {
    WhatsUpDawgTheme {
        BreedCard(
            breed = BreedUiModel(
                id = "",
                name = "German sheppard",
                imageUrl = "https://images.dog.ceo/breeds/hound-basset/n02088238_10510.jpg"
            ),
            onClick = {}
        )
    }
}
