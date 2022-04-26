package com.mupper.skeletonlogin.presentation.ui.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.orbitmvi.orbit.test

@ExperimentalCoroutinesApi
class WelcomeViewModelTest {

    private lateinit var welcomeViewModel: WelcomeViewModel

    @Before
    fun setUp() {
        welcomeViewModel = WelcomeViewModel()
    }

    @Test
    fun `onSignUpClick should call postSideEffect with NavigateToSignUp given testContainerHost`() =
        runTest {
            // Given
            val initialState = WelcomeViewModel.State()
            val testContainerHost = welcomeViewModel.test(initialState)

            // When
            testContainerHost.testIntent {
                welcomeViewModel.onSignUpClick()
            }

            // Then
            testContainerHost.assert(initialState) {
                postedSideEffects(WelcomeViewModel.SideEffect.NavigateToSignUp)
            }
        }

    @Test
    fun `onLoginClick should call postSideEffect with NavigateToLogin given testContainerHost`() =
        runTest {
            // Given
            val initialState = WelcomeViewModel.State()
            val testContainerHost = welcomeViewModel.test(initialState)

            // When
            testContainerHost.testIntent {
                welcomeViewModel.onLoginClick()
            }

            // Then
            testContainerHost.assert(initialState) {
                postedSideEffects(WelcomeViewModel.SideEffect.NavigateToLogin)
            }
        }
}