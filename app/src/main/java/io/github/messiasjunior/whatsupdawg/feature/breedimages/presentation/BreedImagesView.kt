package io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.messiasjunior.whatsupdawg.R
import io.github.messiasjunior.whatsupdawg.core.ui.theme.TitleFontFamily
import io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation.BreedImagesViewModel.UiState
import io.github.messiasjunior.whatsupdawg.feature.common.ErrorView
import io.github.messiasjunior.whatsupdawg.feature.common.LoadingView
import kotlinx.coroutines.FlowPreview
import java.util.concurrent.atomic.AtomicBoolean

@FlowPreview
@ExperimentalMaterial3Api
@Composable
fun BreedImagesView(
    navController: NavHostController,
    viewModel: BreedImagesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val shouldFetchData = rememberSaveable { AtomicBoolean(true) }
    if(shouldFetchData.getAndSet(false)) {
        viewModel.loadSubBreeds()
    }

    Column {
        TopBar(breedName = viewModel.mainBreedName) {
            navController.navigateUp()
        }

        AnimatedVisibility(
            visible = uiState is UiState.Error,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            ErrorView(message = stringResource(id = R.string.error_message_breeds_images)) {
                viewModel.loadSubBreeds()
            }
        }

        AnimatedVisibility(
            visible = uiState is UiState.Loading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LoadingView()
        }

        AnimatedVisibility(
            visible = uiState is UiState.Success,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            (uiState as? UiState.Success)?.let {
                BreedImagesListView(it.uiModels)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun TopBar(breedName: String, onBackClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.what_up_breed, breedName),
                fontFamily = TitleFontFamily
            )
        },

        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.navigate_back_a11y)
                )
            }
        },
    )
}
