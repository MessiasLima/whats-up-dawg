package io.github.messiasjunior.whatsupdawg.feature.breedimages

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import io.github.messiasjunior.whatsupdawg.R
import io.github.messiasjunior.whatsupdawg.core.ui.theme.TitleFontFamily

@ExperimentalMaterial3Api
@Composable
fun BreedImagesView(navController: NavHostController, breedName: String) {
    Column {
        TopBar(breedName = breedName) {
            navController.navigateUp()
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun TopBar(breedName: String, onBackClick: ()->Unit) {
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
