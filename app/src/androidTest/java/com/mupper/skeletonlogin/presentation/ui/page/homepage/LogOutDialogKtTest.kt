package com.mupper.skeletonlogin.presentation.ui.page.homepage

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.mupper.skeletonlogin.presentation.ui.theme.SkeletonLoginTheme
import io.mockk.*
import org.junit.Rule
import org.junit.Test


class LogOutDialogKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun contentShouldDisplayNodeWithTextYaTeVasGivenOpenDialogIsTrue() {
        // Given
        val openDialog = true

        // When
        composeTestRule.setContent {
            SkeletonLoginTheme {
                LogOutDialog(
                    openDialog = openDialog,
                    onDismissDialog = {},
                    logOut = {},
                )
            }
        }

        // Then
        composeTestRule.onNodeWithText("¿Ya te vas?").assertIsDisplayed()
    }

    @Test
    fun contentShouldNotDisplayNodeWithTextYaTeVasGivenOpenDialogIsFalse() {
        // Given
        val openDialog = false

        // When
        composeTestRule.setContent {
            SkeletonLoginTheme {
                LogOutDialog(
                    openDialog = openDialog,
                    onDismissDialog = {},
                    logOut = {},
                )
            }
        }

        // Then
        composeTestRule.onNodeWithText("¿Ya te vas?").assertDoesNotExist()
    }

    @Test
    fun clickOnNodeWithTextCANCELARShouldCallOnDismissDialog() {
        // Given
        val openDialog = true
        val onDismissDialog: () -> Unit = mockk()
        every { onDismissDialog() } just runs
        composeTestRule.setContent {
            SkeletonLoginTheme {
                LogOutDialog(
                    openDialog = openDialog,
                    onDismissDialog = onDismissDialog,
                    logOut = {},
                )
            }
        }

        // When
        composeTestRule.onNodeWithText("CANCELAR").performClick()

        // Then
        verify { onDismissDialog() }
    }

    @Test
    fun clickOnNodeWithTextCERRAR_SESIONShouldCallLogOut() {
        // Given
        val openDialog = true
        val logOut: () -> Unit = mockk()
        every { logOut() } just runs
        composeTestRule.setContent {
            SkeletonLoginTheme {
                LogOutDialog(
                    openDialog = openDialog,
                    onDismissDialog = {},
                    logOut = logOut,
                )
            }
        }

        // When
        composeTestRule.onNodeWithText("CERRAR SESIÓN").performClick()

        // Then
        verify { logOut() }
    }
}