package io.github.messiasjunior.whatsupdawg.feature.breeds

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import io.github.messiasjunior.whatsupdawg.feature.breedimages.navigateToBreedImages

@Composable
fun BreedsView(navController: NavHostController, viewModel: BreedsViewModel = viewModel()) {
    Column {
        Text(text = viewModel.message)
        Button(onClick = {
            navController.navigateToBreedImages("Some amazing name")
        }) {
            Text(text = "Navigate to breed images")
        }
    }
}
