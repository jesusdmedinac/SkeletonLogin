package com.mupper.skeletonlogin.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class WelcomeViewModel : ViewModel(), ContainerHost<WelcomeViewModel.State, WelcomeViewModel.SideEffect> {

    override val container: Container<State, SideEffect> =
        container(State())

    fun onSignUpClick() = intent {
        postSideEffect(SideEffect.NavigateToSignUp)
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