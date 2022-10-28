package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.messiasjunior.whatsupdawg.R
import io.github.messiasjunior.whatsupdawg.core.ui.theme.TitleFontFamily
import io.github.messiasjunior.whatsupdawg.feature.breedimages.navigateToBreedImages
import io.github.messiasjunior.whatsupdawg.feature.breeds.presentation.BreedsViewModel.UiState
import io.github.messiasjunior.whatsupdawg.feature.common.ErrorView
import io.github.messiasjunior.whatsupdawg.feature.common.LoadingView
import kotlinx.coroutines.FlowPreview

@ExperimentalMaterial3Api
@FlowPreview
@Composable
fun BreedsView(navController: NavHostController, viewModel: BreedsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState(initial = UiState.Idle)

    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontFamily = TitleFontFamily
                )
            }
        )

        AnimatedVisibility(
            visible = uiState is UiState.Loading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LoadingView()
        }

        AnimatedVisibility(visible = uiState is UiState.Error, enter = fadeIn(), exit = fadeOut()) {
            ErrorView(message = stringResource(id = R.string.error_message_breeds_list)) { viewModel.loadBreeds() }
        }

        AnimatedVisibility(
            visible = uiState is UiState.Success,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            BreedsListView((uiState as UiState.Success).breeds) {
                navController.navigateToBreedImages(it)
            }
        }
    }
}
