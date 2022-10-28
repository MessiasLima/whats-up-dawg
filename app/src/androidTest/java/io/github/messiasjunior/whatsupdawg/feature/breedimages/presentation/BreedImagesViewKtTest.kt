package io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation

import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.messiasjunior.whatsupdawg.MainActivity
import io.github.messiasjunior.whatsupdawg.R
import io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation.BreedImagesViewModel.UiState.Error
import io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation.BreedImagesViewModel.UiState.Idle
import io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation.BreedImagesViewModel.UiState.Loading
import io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation.BreedImagesViewModel.UiState.Success
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@FlowPreview
@ExperimentalMaterial3Api
@HiltAndroidTest
class BreedImagesViewKtTest {
    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val mockViewModel = mockk<BreedImagesViewModel>()
    private val mockNavController = mockk<NavHostController>()

    @Before
    fun setup() {
        every { mockViewModel.loadSubBreeds() } returns Unit
        every { mockViewModel.uiState } returns MutableStateFlow(Idle)
        every { mockViewModel.mainBreedName } returns ""
    }

    @Test
    fun shouldShowLoadingView() {
        every { mockViewModel.uiState } returns MutableStateFlow(Loading)

        composeRule.activity.setContent {
            BreedImagesView(
                navController = mockNavController,
                viewModel = mockViewModel,
            )
        }

        composeRule.onNodeWithText(composeRule.activity.getString(R.string.loading))
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun shouldShowErrorView() {
        every { mockViewModel.uiState } returns MutableStateFlow(Error)

        composeRule.activity.setContent {
            BreedImagesView(
                navController = mockNavController,
                viewModel = mockViewModel,
            )
        }

        composeRule.onNodeWithText(composeRule.activity.getString(R.string.error_title))
            .assertExists()
            .assertIsDisplayed()

        composeRule.onNodeWithText(composeRule.activity.getString(R.string.error_message_breeds_images))
            .assertExists()
            .assertIsDisplayed()

        composeRule.onNodeWithText(composeRule.activity.getString(R.string.try_again))
            .assertExists()
            .performClick()

        verify(exactly = 2) { mockViewModel.loadSubBreeds() }
    }

    @Test
    fun shouldShowMainBreedNameOnToolbar() {
        val breedName = "Main breed"
        every { mockViewModel.mainBreedName } returns breedName

        composeRule.activity.setContent {
            BreedImagesView(
                navController = mockNavController,
                viewModel = mockViewModel,
            )
        }

        composeRule.onNodeWithText(
            composeRule.activity.getString(
                R.string.what_up_breed,
                breedName
            )
        )
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun shouldNavigateBack() {
        every { mockNavController.navigateUp() } returns true

        composeRule.activity.setContent {
            BreedImagesView(
                navController = mockNavController,
                viewModel = mockViewModel,
            )
        }

        composeRule.onNodeWithContentDescription(composeRule.activity.getString(R.string.navigate_back_a11y))
            .assertExists()
            .assertIsDisplayed()
            .performClick()

        verify { mockNavController.navigateUp() }
    }

    @Test
    fun shouldLoadImagesSingleBreed() {
        val breedName = "Breed name"
        val images = listOf(
            "image1",
            "image2",
            "image3"
        )
        val list = listOf(BreedImagesUiModel(breedName = breedName, images = images))
        every { mockViewModel.uiState } returns MutableStateFlow(Success(list))

        composeRule.activity.setContent {
            BreedImagesView(
                navController = mockNavController,
                viewModel = mockViewModel,
            )
        }

        composeRule.onNodeWithText(breedName)
            .assertDoesNotExist()

        composeRule.onNodeWithContentDescription("$breedName 0")
            .assertIsDisplayed()

        composeRule.onNodeWithContentDescription("$breedName 1")
            .assertIsDisplayed()

        composeRule.onNodeWithContentDescription("$breedName 2")
            .assertIsDisplayed()
    }

    @Test
    fun shouldLoadImagesMultiBreed() {
        val breedName = "Poodle toy"
        val breedName2 = "Poodle standard"

        val images = listOf(
            "image1",
            "image2",
        )
        val list = listOf(
            BreedImagesUiModel(breedName = breedName, images = images),
            BreedImagesUiModel(breedName = breedName2, images = images),
        )
        every { mockViewModel.uiState } returns MutableStateFlow(Success(list))

        composeRule.activity.setContent {
            BreedImagesView(
                navController = mockNavController,
                viewModel = mockViewModel,
            )
        }

        composeRule.onNodeWithText(breedName)
            .assertIsDisplayed()

        composeRule.onNodeWithContentDescription("$breedName 0")
            .assertIsDisplayed()

        composeRule.onNodeWithContentDescription("$breedName 1")
            .assertIsDisplayed()

        composeRule.onNodeWithText(breedName2)
            .assertIsDisplayed()

        composeRule.onNodeWithContentDescription("$breedName2 0")
            .assertIsDisplayed()

        composeRule.onNodeWithContentDescription("$breedName2 1")
            .assertIsDisplayed()

    }
}
