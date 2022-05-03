package com.mupper.skeletonlogin.presentation.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.orbitmvi.orbit.test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var initialState: HomeViewModel.State

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel()

        initialState = HomeViewModel.State()
    }

    @Test
    fun `onBackClick should update isLogOutDialogDisplayed as true`() = runTest {
        // Given
        val testContainerHost = homeViewModel.test(initialState)

        // When
        testContainerHost.testIntent {
            homeViewModel.onBackClick()
        }

        // Then
        testContainerHost.assert(initialState) {
            states(
                { copy(isLogOutDialogDisplayed = true) }
            )
        }
    }

    @Test
    fun `onLogOutDialogDismissClick should update isLogOutDialogDisplayed as false`() = runTest {
        // Given
        val testContainerHost = homeViewModel.test(initialState)

        // When
        testContainerHost.testIntent {
            homeViewModel.onLogOutDialogDismissClick()
        }

        // Then
        testContainerHost.assert(initialState) {
            states(
                { copy(isLogOutDialogDisplayed = false) }
            )
        }
    }

    @Test
    fun `onLogOutClick should call postSideEffect with LogOut`() = runTest {
        // Given
        val testContainerHost = homeViewModel.test(initialState)

        // When
        testContainerHost.testIntent {
            homeViewModel.onLogOutClick()
        }

        // Then
        testContainerHost.assert(initialState) {
            postedSideEffects(HomeViewModel.SideEffect.LogOut)
        }
    }
}