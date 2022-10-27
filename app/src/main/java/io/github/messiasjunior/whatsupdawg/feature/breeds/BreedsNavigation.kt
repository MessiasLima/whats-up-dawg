package io.github.messiasjunior.whatsupdawg.feature.breeds

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object BreedsNavigation {
    const val DESTINATION = "breeds"
}

fun NavGraphBuilder.setupBreedsNavigation() {
    composable(route = BreedsNavigation.DESTINATION) {
        BreedsView()
    }
}
