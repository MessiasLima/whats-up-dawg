package io.github.messiasjunior.whatsupdawg.feature.breedimages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import io.github.messiasjunior.whatsupdawg.feature.breeds.navigateToBreeds

@Composable
fun BreedImagesView(navController: NavHostController, breedName: String, ) {
    Column {
        Text(text = breedName)
        Button(onClick = {
            navController.navigateToBreeds()
        }) {
            Text(text = "Navigate to breed list")
        }
    }
}
