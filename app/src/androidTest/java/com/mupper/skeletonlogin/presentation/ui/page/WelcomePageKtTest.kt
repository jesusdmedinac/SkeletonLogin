package com.mupper.skeletonlogin.presentation.ui.page

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.mupper.skeletonlogin.R
import com.mupper.skeletonlogin.presentation.ui.theme.SkeletonLoginTheme
import io.mockk.*
import org.junit.Rule
import org.junit.Test

@ExperimentalUnitApi
class WelcomePageKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun performClickOnNodeWithTextCREAR_CUENTAShouldCallOnSignUpClick() {
        // Given
        val onSignUpClick: () -> Unit = mockk()
        every { onSignUpClick() } just runs
        composeTestRule.setContent {
            SkeletonLoginTheme {
                WelcomePage(
                    onSignUpClick = onSignUpClick,
                    onLoginClick = {},
                )
            }
        }

        // When
        composeTestRule.onNodeWithText("CREAR CUENTA").performClick()

        // Then
        verify { onSignUpClick() }
    }

    @Test
    fun performClickOnNodeWithTextIniciaSesiónShouldCallOnLoginClick() {
        // Given
        val onLoginClick: () -> Unit = mockk()
        every { onLoginClick() } just runs
        composeTestRule.setContent {
            SkeletonLoginTheme {
                WelcomePage(
                    onSignUpClick = {},
                    onLoginClick = onLoginClick,
                )
            }
        }

        // When
        composeTestRule.onNodeWithText("Inicia Sesión").performClick()

        // Then
        verify { onLoginClick() }
    }
}