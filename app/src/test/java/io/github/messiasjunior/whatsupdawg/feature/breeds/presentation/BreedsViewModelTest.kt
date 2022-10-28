package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import io.github.messiasjunior.whatsupdawg.core.testing.MainDispatcherRule
import io.github.messiasjunior.whatsupdawg.core.testing.TestDispatcherProvider
import io.github.messiasjunior.whatsupdawg.core.testing.UnitTest
import io.github.messiasjunior.whatsupdawg.domain.Breed
import io.github.messiasjunior.whatsupdawg.feature.breeds.usecase.BreedRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import io.github.messiasjunior.whatsupdawg.feature.breeds.presentation.BreedsViewModel.UiState
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert.assertTrue

@FlowPreview
@ExperimentalCoroutinesApi
class BreedsViewModelTest : UnitTest<BreedsViewModel>() {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockBreedRepository = mockk<BreedRepository>()
    private val mockBreedUiModelMapper = mockk<BreedUiModelMapper>()

    override fun buildSut() = BreedsViewModel(
        breedRepository = mockBreedRepository,
        breedUiModelMapper = mockBreedUiModelMapper,
        dispatcherProvider = TestDispatcherProvider()
    )

    @Test
    fun `should load breeds`() = runTest {
        val fixtBreedList = fixture<List<Breed>>()
        val fixtBreedUiModelList = fixture<List<BreedUiModel>>()

        every { mockBreedRepository.findAll() } returns flow {
            delay(2)
            emit(fixtBreedList)
        }
        every { mockBreedUiModelMapper.map(fixtBreedList) } returns fixtBreedUiModelList

        sut.loadBreeds()

        assertEquals(UiState.Idle, sut.uiState.value)
        advanceTimeBy(1)
        assertEquals(UiState.Loading, sut.uiState.value)
        advanceUntilIdle()
        assertTrue(sut.uiState.value is UiState.Success)
        assertEquals(fixtBreedUiModelList, (sut.uiState.value as UiState.Success).breeds)
    }
}
