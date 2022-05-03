package com.mupper.skeletonlogin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() : ViewModel(),
    ContainerHost<WelcomeViewModel.State, WelcomeViewModel.SideEffect> {
    override val container: Container<State, SideEffect> =
        container(State())

    private suspend fun SimpleSyntax<State, SideEffect>.postRepetitiveSideEffect(
        sideEffect: SideEffect
    ) {
        postSideEffect(sideEffect)
        delay(100)
        postSideEffect(SideEffect.Idle)
    }

    fun onSignUpClick() = intent {
        postRepetitiveSideEffect(SideEffect.NavigateToSignUp)
    }

    fun onLoginClick() = intent {
        postSideEffect(SideEffect.NavigateToLogin)
    }

    class State

    sealed class SideEffect {
        object Idle : SideEffect()
        object NavigateToSignUp : SideEffect()
        object NavigateToLogin : SideEffect()
    }
}