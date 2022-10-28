package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.messiasjunior.whatsupdawg.feature.breeds.usecase.BreedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val breedRepository: BreedRepository,
    private val breedUiModelMapper: BreedUiModelMapper,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState by lazy {
        loadBreeds()
        _uiState.asStateFlow()
    }

    fun loadBreeds() {
        breedRepository.findAll()
            .map { breedUiModelMapper.map(it) }
            .onStart { _uiState.value = UiState.Loading }
            .catch { _uiState.value = UiState.Error }
            .onEach { _uiState.value = UiState.Success(it) }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    sealed class UiState {
        object Idle : UiState()
        object Loading : UiState()
        object Error : UiState()
        class Success(val breeds: List<BreedUiModel>) : UiState()
    }
}
