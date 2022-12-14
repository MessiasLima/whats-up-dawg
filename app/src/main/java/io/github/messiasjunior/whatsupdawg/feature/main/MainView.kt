package io.github.messiasjunior.whatsupdawg.feature.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.messiasjunior.whatsupdawg.feature.breedimages.setupBreedImagesNavigation
import io.github.messiasjunior.whatsupdawg.feature.breeds.BreedsNavigation
import io.github.messiasjunior.whatsupdawg.feature.breeds.setupBreedsNavigation
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalMaterial3Api
@Composable
fun MainView() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = BreedsNavigation.DESTINATION) {
        setupBreedsNavigation(navController)
        setupBreedImagesNavigation(navController)
    }
}
