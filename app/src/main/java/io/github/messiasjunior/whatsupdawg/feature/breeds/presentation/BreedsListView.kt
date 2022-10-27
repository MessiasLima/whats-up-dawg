package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import io.github.messiasjunior.whatsupdawg.domain.Breed

@Composable
fun BreedsListView(breeds: List<Breed>, onBreedClick: (Breed) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = Spacing.SMALL)) {
        LazyColumn {
            items(breeds) { breed ->
                Spacer(modifier = Modifier.height(Spacing.SMALL))
                BreedCard(breed = breed, onClick = { onBreedClick(breed) })
            }
        }
    }
}

@Composable
private fun BreedCard(breed: Breed, onClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .semantics(mergeDescendants = true) {
                contentDescription = breed.name
            },
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(192.dp)
                    .clip(CardDefaults.outlinedShape),
                model = breed.image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.padding(all = Spacing.SMALL),
                text = breed.name,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}


@Composable
@Preview
private fun BreedCardPreview() {
    WhatsUpDawgTheme {
        BreedCard(
            breed = Breed(
                id = "",
                name = "German sheppard",
                subBreeds = emptyList(),
                image = "https://images.dog.ceo/breeds/hound-basset/n02088238_10510.jpg"
            ),
            onClick = {}
        )
    }
}
