package io.github.messiasjunior.whatsupdawg.feature.breedimages

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation.BreedImagesView
import io.github.messiasjunior.whatsupdawg.feature.breeds.presentation.BreedUiModel
import kotlinx.coroutines.FlowPreview

const val BREED_IMAGES_ARGUMENT = "breedId"
private const val BREED_IMAGES_PATH = "breed-images"
private const val BREED_IMAGES_DESTINATION = "$BREED_IMAGES_PATH/{$BREED_IMAGES_ARGUMENT}"

@FlowPreview
@ExperimentalMaterial3Api
fun NavGraphBuilder.setupBreedImagesNavigation(navController: NavHostController) {
    composable(route = BREED_IMAGES_DESTINATION) {
        BreedImagesView(navController = navController)
    }
}

fun NavController.navigateToBreedImages(breed: BreedUiModel) {
    navigate("$BREED_IMAGES_PATH/${breed.id}")
}
