package io.github.messiasjunior.whatsupdawg.feature.breeds

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

object BreedsNavigation {
    const val DESTINATION = "breeds"
}

fun NavGraphBuilder.setupBreedsNavigation(navController: NavHostController) {
    composable(route = BreedsNavigation.DESTINATION) {
        BreedsView(navController)
    }
}

fun NavController.navigateToBreeds() {
    navigate(BreedsNavigation.DESTINATION)
}
