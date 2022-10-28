package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.messiasjunior.whatsupdawg.MainActivity
import io.github.messiasjunior.whatsupdawg.R
import io.github.messiasjunior.whatsupdawg.feature.breeds.presentation.BreedsViewModel.UiState.Loading
import io.github.messiasjunior.whatsupdawg.feature.breeds.presentation.BreedsViewModel.UiState.Error
import io.github.messiasjunior.whatsupdawg.feature.breeds.presentation.BreedsViewModel.UiState.Success
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@FlowPreview
@ExperimentalMaterial3Api
@HiltAndroidTest
class BreedsViewKtTest {
    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val mockNavController = mockk<NavHostController>()
    private val mockBreedsViewModel = mockk<BreedsViewModel>()

    @Before
    fun setup() {
        every { mockBreedsViewModel.loadBreeds() } returns Unit
    }

    @Test
    fun shouldShowLoadingView() {
        every { mockBreedsViewModel.uiState } returns MutableStateFlow(Loading)

        composeRule.activity.setContent {
            BreedsView(
                navController = mockNavController,
                viewModel = mockBreedsViewModel,
            )
        }

        composeRule.onNodeWithText(composeRule.activity.getString(R.string.loading))
            .assertExists()
            .assertIsDisplayed()
    }


    @Test
    fun shouldShowErrorView() {
        every { mockBreedsViewModel.uiState } returns MutableStateFlow(Error)

        composeRule.activity.setContent {
            BreedsView(
                navController = mockNavController,
                viewModel = mockBreedsViewModel,
            )
        }

        composeRule.onNodeWithText(composeRule.activity.getString(R.string.error_title))
            .assertExists()
            .assertIsDisplayed()

        composeRule.onNodeWithText(composeRule.activity.getString(R.string.error_message_breeds_list))
            .assertExists()
            .assertIsDisplayed()

        composeRule.onNodeWithText(composeRule.activity.getString(R.string.try_again))
            .assertExists()
            .performClick()

        verify(exactly = 2) { mockBreedsViewModel.loadBreeds() }
    }

    @Test
    fun shouldShowListView() {
        val list = listOf(
            BreedUiModel(id = "breed-1", name = "Breed 1", imageUrl = "url"),
            BreedUiModel(id = "breed-2", name = "Breed 2", imageUrl = "url"),
            BreedUiModel(id = "breed-3", name = "Breed 3", imageUrl = "url"),
        )

        every { mockBreedsViewModel.uiState } returns MutableStateFlow(Success(list))
        list.forEach {
            every { mockNavController.navigate("breed-images/${it.id}", null, null) } returns Unit
        }

        composeRule.activity.setContent {
            BreedsView(
                navController = mockNavController,
                viewModel = mockBreedsViewModel,
            )
        }

        list.forEach {
            composeRule.onNodeWithText(it.name)
                .assertExists()
                .assertIsDisplayed()
                .performClick()
        }

        list.forEach {
            verify {  mockNavController.navigate("breed-images/${it.id}", null, null)  }
        }
    }
}
