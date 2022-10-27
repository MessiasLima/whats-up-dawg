package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.messiasjunior.whatsupdawg.feature.breedimages.navigateToBreedImages
import kotlinx.coroutines.FlowPreview

@FlowPreview
@Composable
fun BreedsView(navController: NavHostController, viewModel: BreedsViewModel = hiltViewModel()) {
    val breeds by viewModel.breeds.collectAsState(initial = emptyList())

    Column {
        LazyColumn {
            items(breeds) {
                Text(text = it.name)
            }
        }
      
        Button(onClick = {
            navController.navigateToBreedImages("Some amazing name")
        }) {
            Text(text = "Navigate to breed images")
        }
    }
}
