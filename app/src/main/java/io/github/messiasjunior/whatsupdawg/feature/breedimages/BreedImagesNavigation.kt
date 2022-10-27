package io.github.messiasjunior.whatsupdawg.feature.breedimages

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

private const val BREED_IMAGES_PATH = "breed-images/{breedName}"
private const val BREED_IMAGES_DESTINATION = "$BREED_IMAGES_PATH/{breedName}"

fun NavGraphBuilder.setupBreedImagesNavigation(navController: NavHostController) {
    composable(route = BREED_IMAGES_DESTINATION) { backStackEntry ->
        val breedName = backStackEntry.arguments?.getString("breedName")
        breedName?.let {
            BreedImagesView(navController = navController, breedName = it)
        }
    }
}

fun NavController.navigateToBreedImages(breedName: String) {
    navigate("$BREED_IMAGES_PATH/$breedName")
}