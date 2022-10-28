package io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation

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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.messiasjunior.whatsupdawg.R
import io.github.messiasjunior.whatsupdawg.core.ui.theme.TitleFontFamily
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalMaterial3Api
@Composable
fun BreedImagesView(
    navController: NavHostController,
    viewModel: BreedImagesViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    uiState.also {
        println(it)
    }

    Column {
        TopBar(breedName = viewModel.mainBreedName) {
            navController.navigateUp()
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun TopBar(breedName: String, onBackClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Whats up $breedName",
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
