package io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation

import androidx.lifecycle.SavedStateHandle
import io.github.messiasjunior.whatsupdawg.core.testing.MainDispatcherRule
import io.github.messiasjunior.whatsupdawg.core.testing.TestDispatcherProvider
import io.github.messiasjunior.whatsupdawg.core.testing.UnitTest
import io.github.messiasjunior.whatsupdawg.domain.SubBreed
import io.github.messiasjunior.whatsupdawg.feature.breedimages.BREED_IMAGES_ARGUMENT
import io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation.BreedImagesViewModel.UiState
import io.github.messiasjunior.whatsupdawg.feature.breedimages.usecase.BreedImagesRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@FlowPreview
class BreedImagesViewModelTest : UnitTest<BreedImagesViewModel>() {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockSavedStateHandle = mockk<SavedStateHandle>()
    private val mockBreedImagesRepository = mockk<BreedImagesRepository>()
    private val mockBreedImagesUiModelMapper = mockk<BreedImagesUiModelMapper>()

    override fun buildSut() = BreedImagesViewModel(
        savedStateHandle = mockSavedStateHandle,
        breedImagesRepository = mockBreedImagesRepository,
        breedImagesUiModelMapper = mockBreedImagesUiModelMapper,
        dispatcherProvider = TestDispatcherProvider()
    )

    @Test
    fun `uiState should star as Idle`() {
        assertEquals(UiState.Idle, sut.uiState.value)
    }

    @Test
    fun `should return main breed name`() {
        val fixtBreed = "breed"
        val fixtBreedName = "Breed"
        every { mockSavedStateHandle.get<String>(BREED_IMAGES_ARGUMENT) } returns fixtBreed
        every { mockBreedImagesUiModelMapper.mapMainBreedName(BreedId(fixtBreed)) } returns fixtBreedName

        assertEquals(fixtBreedName, sut.mainBreedName)
    }

    @Test
    fun `should load sub breeds`() = runTest {
        val fixtBreed = "breed"
        val fixtSubBreedList: List<SubBreed> = fixture()
        val fixtBreedImagesUiModelList: List<BreedImagesUiModel> = fixture()

        every { mockSavedStateHandle.get<String>(BREED_IMAGES_ARGUMENT) } returns fixtBreed
        every { mockBreedImagesRepository.findSubBreedsByBreedId(fixtBreed) } returns flow {
            delay(3)
            emit(fixtSubBreedList)
        }
        every { mockBreedImagesUiModelMapper.map(fixtSubBreedList) } returns fixtBreedImagesUiModelList

        sut.loadSubBreeds()

        assertTrue("state is not idle", sut.uiState.value is UiState.Idle)
        advanceTimeBy(1)
        assertTrue("state is not loading", sut.uiState.value is UiState.Loading)
        advanceUntilIdle()
        assertTrue("state is not success", sut.uiState.value is UiState.Success)
        assertEquals(fixtBreedImagesUiModelList, (sut.uiState.value as UiState.Success).uiModels)
    }
}
