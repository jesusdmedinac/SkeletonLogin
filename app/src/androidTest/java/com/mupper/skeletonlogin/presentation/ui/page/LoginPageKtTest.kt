package com.mupper.skeletonlogin.presentation.ui.page

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.mupper.skeletonlogin.presentation.ui.theme.SkeletonLoginTheme
import com.mupper.skeletonlogin.presentation.viewmodel.LoginViewModel
import io.mockk.*
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random


@ExperimentalUnitApi
class LoginPageKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun contentShouldDisplayANodeWithTextWithExpectedEmailGivenLoginViewModelState() {
        // Given
        val expectedEmail = Random.nextInt().toString()
        val loginViewModelState = LoginViewModel.State(
            email = expectedEmail
        )

        // When
        composeTestRule.setContent {
            SkeletonLoginTheme {
                LoginPage(
                    loginViewModelState = loginViewModelState,
                    onForgotPasswordClick = {},
                    onLoginClick = {},
                    onEmailChange = {},
                    onPasswordChange = {},
                    onBackClick = {},
                    onTogglePasswordVisibilityClick = {},
                )
            }
        }

        // Then
        composeTestRule.onNodeWithText(expectedEmail).assertIsDisplayed()
    }

    @Test
    fun contentShouldDisplayANodeWithTextWithExpectedPasswordGivenLoginViewModelState() {
        // Given
        val expectedPassword = Random.nextInt().toString()
        val loginViewModelState = LoginViewModel.State(
            password = expectedPassword,
            isPasswordVisible = true,
        )

        // When
        composeTestRule.setContent {
            SkeletonLoginTheme {
                LoginPage(
                    loginViewModelState = loginViewModelState,
                    onForgotPasswordClick = {},
                    onLoginClick = {},
                    onEmailChange = {},
                    onPasswordChange = {},
                    onBackClick = {},
                    onTogglePasswordVisibilityClick = {},
                )
            }
        }

        // Then
        composeTestRule.onNodeWithText(expectedPassword).assertIsDisplayed()
    }

    @Test
    fun clickOnOlvidasteTuContrasenaShouldCalOnForgotPasswordClick() {
        // Given
        val onForgotPasswordClick: () -> Unit = mockk()
        every { onForgotPasswordClick() } just runs
        composeTestRule.setContent {
            SkeletonLoginTheme {
                LoginPage(
                    loginViewModelState = LoginViewModel.State(),
                    onForgotPasswordClick = onForgotPasswordClick,
                    onLoginClick = {},
                    onEmailChange = {},
                    onPasswordChange = {},
                    onBackClick = {},
                    onTogglePasswordVisibilityClick = {},
                )
            }
        }

        // When
        composeTestRule.onNodeWithText("¿Olvidaste tu contraseña?").performClick()

        // Then
        verify { onForgotPasswordClick() }
    }

    @Test
    fun clickOnIniciarSesionShouldCalOnLoginClickGivenIsLodingIsFalseAndIsLoginEnabledIsTrue() {
        // Given
        val loginViewModelState = LoginViewModel.State(
            email = Random.nextInt().toString(),
            password = Random.nextInt().toString(),
            isLoading = false,
        )
        val onLoginClick: () -> Unit = mockk()
        every { onLoginClick() } just runs
        composeTestRule.setContent {
            SkeletonLoginTheme {
                LoginPage(
                    loginViewModelState = loginViewModelState,
                    onForgotPasswordClick = {},
                    onLoginClick = onLoginClick,
                    onEmailChange = {},
                    onPasswordChange = {},
                    onBackClick = {},
                    onTogglePasswordVisibilityClick = {},
                )
            }
        }

        // When
        composeTestRule.onNodeWithText("Iniciar sesión").performClick()

        // Then
        verify { onLoginClick() }
    }

    @Test
    fun nodeWithEmailShouldCallOnEmailChangeGivenValueIsChanged() {
        // Given
        val email = Random.nextInt().toString()
        val onEmailChange: (String) -> Unit = mockk()
        every { onEmailChange(email) } just runs
        composeTestRule.setContent {
            SkeletonLoginTheme {
                LoginPage(
                    loginViewModelState = LoginViewModel.State(),
                    onForgotPasswordClick = {},
                    onLoginClick = {},
                    onEmailChange = onEmailChange,
                    onPasswordChange = {},
                    onBackClick = {},
                    onTogglePasswordVisibilityClick = {},
                )
            }
        }

        // When
        composeTestRule.onNodeWithText("Correo electrónico").performTextInput(email)

        // Then
        verify { onEmailChange(email) }
    }

    @Test
    fun nodeWithContrasenaShouldCallOnPasswordChangeGivenValueIsChanged() {
        // Given
        val password = Random.nextInt().toString()
        val onPasswordChange: (String) -> Unit = mockk()
        every { onPasswordChange(password) } just runs
        composeTestRule.setContent {
            SkeletonLoginTheme {
                LoginPage(
                    loginViewModelState = LoginViewModel.State(),
                    onForgotPasswordClick = {},
                    onLoginClick = {},
                    onEmailChange = {},
                    onPasswordChange = onPasswordChange,
                    onBackClick = {},
                    onTogglePasswordVisibilityClick = {},
                )
            }
        }

        // When
        composeTestRule.onNodeWithText("Contraseña").performTextInput(password)

        // Then
        verify { onPasswordChange(password) }
    }

    @Test
    fun clickOnNodeWithContentDescriptionBackNavigationShouldCallOnBackClick() {
        // Given
        val onBackClick: () -> Unit = mockk()
        every { onBackClick() } just runs
        composeTestRule.setContent {
            SkeletonLoginTheme {
                LoginPage(
                    loginViewModelState = LoginViewModel.State(),
                    onForgotPasswordClick = {},
                    onLoginClick = {},
                    onEmailChange = {},
                    onPasswordChange = {},
                    onBackClick = onBackClick,
                    onTogglePasswordVisibilityClick = {},
                )
            }
        }

        // When
        composeTestRule.onNodeWithContentDescription("Back Navigation").performClick()

        // Then
        verify { onBackClick() }
    }

    @Test
    fun clickOnNodeWithContentDescriptionVisibilityIconShouldCallOnTogglePasswordVisibilityClickGivenIsPasswordVisibleIsTrue() {
        // Given
        val loginViewModelState = LoginViewModel.State(
            isPasswordVisible = true
        )
        val onTogglePasswordVisibilityClick: () -> Unit = mockk()
        every { onTogglePasswordVisibilityClick() } just runs
        composeTestRule.setContent {
            SkeletonLoginTheme {
                LoginPage(
                    loginViewModelState = loginViewModelState,
                    onForgotPasswordClick = {},
                    onLoginClick = {},
                    onEmailChange = {},
                    onPasswordChange = {},
                    onBackClick = {},
                    onTogglePasswordVisibilityClick = onTogglePasswordVisibilityClick,
                )
            }
        }

        // When
        composeTestRule.onNodeWithContentDescription("Visibility Icon").performClick()

        // Then
        verify { onTogglePasswordVisibilityClick() }
    }
}