package com.mupper.skeletonlogin.presentation.viewmodel

import com.mupper.skeletonlogin.domain.exception.LoginException
import com.mupper.skeletonlogin.domain.model.UserCredentials
import com.mupper.skeletonlogin.domain.usecase.LoginUseCase
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.orbitmvi.orbit.*
import kotlin.random.Random

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    @MockK
    lateinit var loginUseCase: LoginUseCase

    @InjectMockKs
    lateinit var loginViewModel: LoginViewModel

    private lateinit var initialState: LoginViewModel.State

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        coEvery { loginUseCase(any()) } just runs

        initialState = LoginViewModel.State()
    }

    @Test
    fun `onForgotPasswordClick should should call postSideEffect with NavigateToForgotPassword given testContainerHost`() =
        runTest {
            // Given
            val testContainerHost = loginViewModel.test(initialState)

            // When
            testContainerHost.testIntent {
                loginViewModel.onForgotPasswordClick()
            }

            // Then
            testContainerHost.assert(initialState) {
                postedSideEffects(LoginViewModel.SideEffect.NavigateToForgotPassword)
            }
        }

    @Test
    fun `onLoginClick should reduce isLoading as true and then as false and call postSideEffect with LaunchLoginFailed given loginUseCase throws LoginException`() =
        runTest {
            // Given
            val testContainerHost = loginViewModel.test(initialState)
            val email = initialState.email
            val password = initialState.password
            val userCredentials = UserCredentials(email, password)
            coEvery { loginUseCase(userCredentials) } throws LoginException()

            // When
            testContainerHost.testIntent {
                loginViewModel.onLoginClick()
            }

            // Then
            testContainerHost.assert(initialState) {
                states(
                    { copy(isLoading = true) },
                    { copy(isLoading = false) }
                )
                postedSideEffects(LoginViewModel.SideEffect.LaunchLoginFailed)
            }
        }

    @Test
    fun `onLoginClick should call postSideEffect given loginUseCase does not throw LoginException`() =
        runTest {
            // Given
            val testContainerHost = loginViewModel.test(initialState)
            val email = initialState.email
            val password = initialState.password
            val userCredentials = UserCredentials(email, password)
            coEvery { loginUseCase(userCredentials) } just runs

            // When
            testContainerHost.testIntent {
                loginViewModel.onLoginClick()
            }

            // Then
            testContainerHost.assert(initialState) {
                states(
                    { copy(isLoading = true) },
                    { copy(isLoading = false) }
                )
                postedSideEffects(LoginViewModel.SideEffect.NavigateToHomePage)
            }
        }

    @Test
    fun `onEmailChangeShould should update email given expectedEmail`() = runTest {
        // Given
        val testContainerHost = loginViewModel.test(initialState)

        // When
        val expectedEmail = Random.nextInt().toString()
        testContainerHost.testIntent {
            loginViewModel.onEmailChange(expectedEmail)
        }

        // Then
        testContainerHost.assert(initialState) {
            states(
                { copy(email = expectedEmail) }
            )
        }
    }

    @Test
    fun `onPasswordChange should update password given expectedPassword`() = runTest {
        // Given
        val testContainerHost = loginViewModel.test(initialState)

        // When
        val expectedPassword = Random.nextInt().toString()
        testContainerHost.testIntent {
            loginViewModel.onPasswordChange(expectedPassword)
        }

        // Then
        testContainerHost.assert(initialState) {
            states(
                { copy(password = expectedPassword) }
            )
        }
    }

    @Test
    fun `onBackClick should call postSideEffect with NavigateToWelcomePage given testContainerHost`() =
        runTest {
            // Given
            val testContainerHost = loginViewModel.test(initialState)

            // When
            testContainerHost.testIntent {
                loginViewModel.onBackClick()
            }

            // Then
            testContainerHost.assert(initialState) {
                postedSideEffects(LoginViewModel.SideEffect.NavigateToWelcomePage)
            }
        }

    @Test
    fun `onTogglePasswordVisibility should toggle isPasswordVisible given testContainerHost`() =
        runTest {
            // Given
            val isPasswordVisible = Random.nextBoolean()
            val initialState = LoginViewModel.State(isPasswordVisible = isPasswordVisible)
            val testContainerHost = loginViewModel.test(initialState)

            // When
            testContainerHost.testIntent {
                loginViewModel.onTogglePasswordVisibility()
            }

            // Then
            val expectedIsPasswordVisible = !isPasswordVisible
            testContainerHost.assert(initialState) {
                states(
                    { copy(isPasswordVisible = expectedIsPasswordVisible) }
                )
            }
        }

    @Test
    fun `isLoginEnabled should be true given email and password `() = runTest {
        // Given
        loginViewModel.test(initialState.copy(
            email = Random.nextInt().toString(),
            password = Random.nextInt().toString(),
        ))

        // When
        val result = loginViewModel.container.stateFlow.value.isLoginEnabled

        // Then
        assertThat(result, `is`(true))
    }

    @Test
    fun `isLoginEnabled should be false given email and password `() = runTest {
        // Given
        loginViewModel.test(initialState)

        // When
        val result = loginViewModel.container.stateFlow.value.isLoginEnabled

        // Then
        assertThat(result, `is`(false))
    }
}