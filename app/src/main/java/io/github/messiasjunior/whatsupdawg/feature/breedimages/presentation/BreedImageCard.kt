package io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.messiasjunior.whatsupdawg.core.ui.theme.Spacing
import io.github.messiasjunior.whatsupdawg.core.ui.theme.WhatsUpDawgTheme

@Composable
fun BreedImageCard(modifier: Modifier = Modifier, breedName: String, imageUrl: String, index: Int) {
    OutlinedCard(modifier = modifier.padding(all = Spacing.VerySmall)) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(192.dp)
                .clip(CardDefaults.outlinedShape),
            model = imageUrl,
            contentDescription = "$breedName $index",
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
@Preview
private fun BreedImageCardPreview() {
    WhatsUpDawgTheme() {
        BreedImageCard(breedName = "Breed", imageUrl = "", index = 0)
    }
}
