package com.mupper.skeletonlogin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(), ContainerHost<HomeViewModel.State, HomeViewModel.SideEffect> {
    override val container: Container<State, SideEffect> =
        container(State())

    fun onBackClick() = intent {
        reduce {
            state.copy(
                isLogOutDialogDisplayed = true
            )
        }
    }

    fun onLogOutDialogDismissClick() = intent {
        reduce {
            state.copy(
                isLogOutDialogDisplayed = false
            )
        }
    }

    fun onLogOutClick() = intent {
        postSideEffect(SideEffect.LogOut)
    }

    data class State(
        val isLogOutDialogDisplayed: Boolean = false,
    )

    sealed class SideEffect {
        object Idle : SideEffect()
        object LogOut : SideEffect()
    }
}