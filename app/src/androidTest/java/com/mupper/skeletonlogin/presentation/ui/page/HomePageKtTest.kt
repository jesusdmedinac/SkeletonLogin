package com.mupper.skeletonlogin.presentation.ui.page

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.mupper.skeletonlogin.presentation.ui.theme.SkeletonLoginTheme
import com.mupper.skeletonlogin.presentation.viewmodel.HomeViewModel
import io.mockk.*
import org.junit.Rule
import org.junit.Test


@ExperimentalUnitApi
class HomePageKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun contentShouldDisplayNodeWithTextYaTeVasGivenIsLogOutDialogDisplayedIsTrue() {
        // Given
        val homeViewModelState = HomeViewModel.State(
            isLogOutDialogDisplayed = true
        )

        // When
        composeTestRule.setContent {
            SkeletonLoginTheme {
                HomePage(
                    homeViewModelState = homeViewModelState,
                    onBackClick = {},
                    onLogOutDialogDismissClick = {},
                    onLogOutClick = {}
                )
            }
        }

        // Then
        composeTestRule.onNodeWithText("¿Ya te vas?").assertIsDisplayed()
    }

    @Test
    fun contentShouldNotDisplayNodeWithTextYaTeVasGivenIsLogOutDialogDisplayedIsFalse() {
        // Given
        val homeViewModelState = HomeViewModel.State(
            isLogOutDialogDisplayed = false
        )

        // When
        composeTestRule.setContent {
            SkeletonLoginTheme {
                HomePage(
                    homeViewModelState = homeViewModelState,
                    onBackClick = {},
                    onLogOutDialogDismissClick = {},
                    onLogOutClick = {}
                )
            }
        }

        // Then
        composeTestRule.onNodeWithText("¿Ya te vas?").assertDoesNotExist()
    }

    @Test
    fun clickOnNodeWithContentDescriptionBackNavigationShouldCallOnBackClick() {
        // Given
        val onBackClick: () -> Unit = mockk()
        every { onBackClick() } just runs
        composeTestRule.setContent {
            SkeletonLoginTheme {
                HomePage(
                    homeViewModelState = HomeViewModel.State(),
                    onBackClick = onBackClick,
                    onLogOutDialogDismissClick = {},
                    onLogOutClick = {}
                )
            }
        }

        // When
        composeTestRule.onNodeWithContentDescription("Back Navigation").performClick()

        // Then
        verify { onBackClick() }
    }
}