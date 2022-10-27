package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.messiasjunior.whatsupdawg.feature.breeds.usecase.BreedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val breedRepository: BreedRepository
) : ViewModel() {
    val message = "Deu bom"
    val breeds = breedRepository.findAll()
        .flowOn(Dispatchers.IO)
}
