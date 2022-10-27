package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.messiasjunior.whatsupdawg.feature.breeds.usecase.BreedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class BreedsViewModel @Inject constructor(
    breedRepository: BreedRepository
) : ViewModel() {
    val breeds = breedRepository.findAll()
        .flowOn(Dispatchers.IO)
}
