package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.messiasjunior.whatsupdawg.feature.breedimages.navigateToBreedImages
import io.github.messiasjunior.whatsupdawg.feature.breeds.presentation.BreedsViewModel.UiState
import kotlinx.coroutines.FlowPreview

@FlowPreview
@Composable
fun BreedsView(navController: NavHostController, viewModel: BreedsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState(initial = UiState.Idle)
    val isLoading = uiState is UiState.Loading
    val isError = uiState is UiState.Error
    val isSuccess = uiState is UiState.Success

    AnimatedVisibility(visible = isLoading, enter = fadeIn(), exit = fadeOut()) {
        BreedsLoadingView()
    }

    AnimatedVisibility(visible = isError, enter = fadeIn(), exit = fadeOut()) {
        BreedsErrorView { viewModel.loadBreeds() }
    }

    AnimatedVisibility(visible = isSuccess, enter = fadeIn(), exit = fadeOut()) {
        BreedsListView((uiState as UiState.Success).breeds) {
            navController.navigateToBreedImages(it)
        }
    }
}
