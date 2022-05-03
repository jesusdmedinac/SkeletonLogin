package com.mupper.skeletonlogin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.mupper.skeletonlogin.domain.model.UserCredentials
import com.mupper.skeletonlogin.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.OrbitDsl
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel(), ContainerHost<LoginViewModel.State, LoginViewModel.SideEffect> {
    override val container: Container<State, SideEffect> =
        container(State())

    private suspend fun SimpleSyntax<State, SideEffect>.postRepetitiveSideEffect(sideEffect: SideEffect) {
        postSideEffect(sideEffect)
        delay(100)
        postSideEffect(SideEffect.Idle)
    }

    fun onForgotPasswordClick() = intent {
        postRepetitiveSideEffect(SideEffect.NavigateToForgotPassword)
    }

    fun onLoginClick() = intent {
        reduce {
            state.copy(isLoading = true)
        }
        val email = state.email
        val password = state.password
        val userCredentials = UserCredentials(email, password)
        runCatching { loginUseCase(userCredentials) }
            .onSuccess { navigateToHomePage() }
            .onFailure { onLoginFailed() }
    }

    private suspend fun SimpleSyntax<State, SideEffect>.navigateToHomePage() {
        reduce {
            state.copy(isLoading = false)
        }
        postSideEffect(SideEffect.NavigateToHomePage)
    }

    private suspend fun SimpleSyntax<State, SideEffect>.onLoginFailed() {
        reduce {
            state.copy(isLoading = false)
        }
        postSideEffect(SideEffect.LaunchLoginFailed)
    }

    fun onEmailChange(email: String) = intent {
        reduce {
            state.copy(email = email)
        }
    }

    fun onPasswordChange(password: String) = intent {
        reduce {
            state.copy(password = password)
        }
    }

    fun onBackClick() = intent {
        postSideEffect(SideEffect.NavigateToWelcomePage)
    }

    fun togglePasswordVisibility() = intent {
        reduce {
            state.copy(passwordVisibility = !state.passwordVisibility)
        }
    }

    data class State(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val passwordVisibility: Boolean = false,
    ) {
        val isLoginEnabled get() = email.isNotEmpty() && password.isNotEmpty()
    }

    sealed class SideEffect {
        object Idle : SideEffect()
        object NavigateToHomePage : SideEffect()
        object NavigateToForgotPassword : SideEffect()
        object NavigateToWelcomePage : SideEffect()
        object LaunchLoginFailed : SideEffect()
    }
}