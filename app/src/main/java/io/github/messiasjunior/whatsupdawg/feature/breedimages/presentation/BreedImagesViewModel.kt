package io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BreedImagesViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Success)
    val uiState = _uiState.asStateFlow()

    sealed class UiState {
        object Idle : UiState()
        object Loading : UiState()
        object Error : UiState()
        object Success : UiState()
    }
}
