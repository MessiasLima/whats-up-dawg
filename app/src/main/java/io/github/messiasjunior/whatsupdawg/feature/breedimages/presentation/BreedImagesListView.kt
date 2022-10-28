package io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.messiasjunior.whatsupdawg.core.ui.theme.Spacing

@Composable
fun BreedImagesListView(uiModels: List<BreedImagesUiModel>) {
    if (uiModels.size > 1) {
        MultiBreedImagesList(uiModels)
    } else {
        SingleBreedImagesList(uiModels.first())
    }
}

@Composable
fun SingleBreedImagesList(uiModel: BreedImagesUiModel) {
    LazyVerticalGrid(columns = GridCells.Adaptive(192.dp)) {
        itemsIndexed(uiModel.images) { index, imageUrl ->
            BreedImageCard(breedName = uiModel.breedName, imageUrl = imageUrl, index = index)
        }
    }
}

@Composable
fun MultiBreedImagesList(uiModels: List<BreedImagesUiModel>) {
    LazyColumn {
        items(uiModels) {
            ImageSection(it)
        }
    }
}

@Composable
fun ImageSection(uiModel: BreedImagesUiModel) {
    Spacer(modifier = Modifier.height(Spacing.Small))

    Text(
        modifier = Modifier.padding(start = Spacing.Small),
        text = uiModel.breedName,
        style = MaterialTheme.typography.titleLarge,
    )

    LazyRow {
        itemsIndexed(uiModel.images) { index, item ->
            BreedImageCard(
                modifier = Modifier.width(256.dp),
                breedName = uiModel.breedName,
                imageUrl = item,
                index = index
            )
        }
    }
}
