package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.messiasjunior.whatsupdawg.core.network.Response
import io.github.messiasjunior.whatsupdawg.feature.breedimages.navigateToBreedImages

@Composable
fun BreedsView(navController: NavHostController, viewModel: BreedsViewModel = hiltViewModel()) {
    val breeds by viewModel.breeds.collectAsState(initial = Response(emptyMap(), status = ""))

    breeds.also {
        Log.i("Breeds", breeds.toString())
    }

    Column {
        Text(text = viewModel.message)
        Button(onClick = {
            navController.navigateToBreedImages("Some amazing name")
        }) {
            Text(text = "Navigate to breed images")
        }
    }
}
