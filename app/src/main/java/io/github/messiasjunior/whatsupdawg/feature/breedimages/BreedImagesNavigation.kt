package io.github.messiasjunior.whatsupdawg.feature.breedimages

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import io.github.messiasjunior.whatsupdawg.domain.Breed

private const val BREED_IMAGES_PATH = "breed-images/{breedName}"
private const val BREED_IMAGES_DESTINATION = "$BREED_IMAGES_PATH/{breedName}"

@ExperimentalMaterial3Api
fun NavGraphBuilder.setupBreedImagesNavigation(navController: NavHostController) {
    composable(route = BREED_IMAGES_DESTINATION) { backStackEntry ->
        val breedName = backStackEntry.arguments?.getString("breedName")
        breedName?.let {
            BreedImagesView(navController = navController, breedName = it)
        }
    }
}

fun NavController.navigateToBreedImages(breed: Breed) {
    navigate("$BREED_IMAGES_PATH/${breed.id}")
}
