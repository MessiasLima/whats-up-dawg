package io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.messiasjunior.whatsupdawg.feature.breedimages.usecase.BreedImagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class BreedImagesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val breedImagesRepository: BreedImagesRepository,
    private val breedImagesUiModelMapper: BreedImagesUiModelMapper,
) : ViewModel() {
    private val breedId = BreedId(savedStateHandle)
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState by lazy {
        loadSubBreeds()
        _uiState.asStateFlow()
    }

    val mainBreedName = breedImagesUiModelMapper.mapMainBreedName(breedId)

    fun loadSubBreeds() {
        flowOf(breedId)
            .flatMapConcat { breedImagesRepository.findSubBreedsByBreedId(it.breedId) }
            .onStart { _uiState.value = UiState.Loading }
            .catch { _uiState.value = UiState.Error }
            .map { breedImagesUiModelMapper.map(it) }
            .onEach { _uiState.value = UiState.Success(uiModels = it) }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    sealed class UiState {
        object Idle : UiState()
        object Loading : UiState()
        object Error : UiState()
        class Success(val uiModels: List<BreedImagesUiModel>) : UiState()
    }
}
