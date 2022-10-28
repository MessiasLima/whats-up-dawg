package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.coroutines.FlowPreview

object BreedsNavigation {
    const val DESTINATION = "breeds"
}

@FlowPreview
@ExperimentalMaterial3Api
fun NavGraphBuilder.setupBreedsNavigation(navController: NavHostController) {
    composable(route = BreedsNavigation.DESTINATION) {
        BreedsView(navController)
    }
}

fun NavController.navigateToBreeds() {
    navigate(BreedsNavigation.DESTINATION)
}
