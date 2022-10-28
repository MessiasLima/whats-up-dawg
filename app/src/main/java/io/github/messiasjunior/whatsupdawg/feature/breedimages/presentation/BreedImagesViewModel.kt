package io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.messiasjunior.whatsupdawg.feature.breedimages.usecase.BreedImagesRepository
import io.github.messiasjunior.whatsupdawg.feature.common.dispatcherprovider.DispatcherProvider
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
class BreedImagesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val breedImagesRepository: BreedImagesRepository,
    private val breedImagesUiModelMapper: BreedImagesUiModelMapper,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {
    private val breedId by lazy { BreedId(savedStateHandle) }
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    val mainBreedName by lazy { breedImagesUiModelMapper.mapMainBreedName(breedId) }

    fun loadSubBreeds() {
        breedImagesRepository.findSubBreedsByBreedId(breedId.value)
            .onStart { _uiState.value = UiState.Loading }
            .catch { _uiState.value = UiState.Error }
            .map { breedImagesUiModelMapper.map(it) }
            .onEach { _uiState.value = UiState.Success(uiModels = it) }
            .flowOn(dispatcherProvider.getDispatcher())
            .launchIn(viewModelScope)
    }

    sealed class UiState {
        object Idle : UiState()
        object Loading : UiState()
        object Error : UiState()
        class Success(val uiModels: List<BreedImagesUiModel>) : UiState()
    }
}
