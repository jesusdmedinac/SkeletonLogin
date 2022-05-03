package com.mupper.skeletonlogin.presentation.ui.viewmodel

import com.mupper.skeletonlogin.presentation.viewmodel.WelcomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.SuspendingTestContainerHost
import org.orbitmvi.orbit.test

@ExperimentalCoroutinesApi
class WelcomeViewModelTest {

    @InjectMockKs
    lateinit var welcomeViewModel: WelcomeViewModel

    lateinit var initialState: WelcomeViewModel.State

    lateinit var testContainerHost: SuspendingTestContainerHost<WelcomeViewModel.State, WelcomeViewModel.SideEffect, ContainerHost<WelcomeViewModel.State, WelcomeViewModel.SideEffect>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        initialState = WelcomeViewModel.State()
        testContainerHost = welcomeViewModel.test(initialState)
    }

    @Test
    fun `onSignUpClick should call postSideEffect with NavigateToSignUp given testContainerHost`() =
        runTest {
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