package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.messiasjunior.whatsupdawg.R
import io.github.messiasjunior.whatsupdawg.feature.breedimages.navigateToBreedImages
import io.github.messiasjunior.whatsupdawg.feature.breeds.presentation.BreedsViewModel.UiState
import kotlinx.coroutines.FlowPreview

@ExperimentalMaterial3Api
@FlowPreview
@Composable
fun BreedsView(navController: NavHostController, viewModel: BreedsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState(initial = UiState.Idle)
    val isLoading = uiState is UiState.Loading
    val isError = uiState is UiState.Error
    val isSuccess = uiState is UiState.Success

    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily(listOf(Font(R.font.pacifico_regular)))
                )
            }
        )

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
}
