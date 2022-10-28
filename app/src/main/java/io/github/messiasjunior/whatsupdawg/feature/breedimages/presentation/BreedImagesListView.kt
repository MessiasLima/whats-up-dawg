package io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun BreedImagesListView(uiModels: List<BreedImagesUiModel>) {
    if (uiModels.size > 1) {
        MultiBreedImagesList(uiModels)
    } else {
        SingleBreedImagesList(uiModels.first())
    }
}

@Composable
fun MultiBreedImagesList(uiModels: List<BreedImagesUiModel>) {

}

@Composable
fun SingleBreedImagesList(uiModel: BreedImagesUiModel) {
    LazyVerticalGrid(columns = GridCells.Adaptive(192.dp)) {
        itemsIndexed(uiModel.images) { index, imageUrl ->
            BreedImageCard(breedName = uiModel.breedName, imageUrl = imageUrl, index = index)
        }
    }
}
