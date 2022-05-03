package com.mupper.skeletonlogin.presentation.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mupper.skeletonlogin.presentation.ui.navigation.NavItem
import com.mupper.skeletonlogin.presentation.ui.pages.LoginPage
import com.mupper.skeletonlogin.presentation.ui.pages.WelcomePage
import com.mupper.skeletonlogin.presentation.viewmodel.LoginViewModel
import com.mupper.skeletonlogin.presentation.viewmodel.WelcomeViewModel

@ExperimentalUnitApi
@Composable
fun SkeletonLoginApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavItem.WelcomeNavItem.baseRoute) {
        composable(NavItem.WelcomeNavItem.baseRoute) {
            val context = LocalContext.current

            val welcomeViewModel: WelcomeViewModel = hiltViewModel()

            val welcomeSideEffect by welcomeViewModel.container.sideEffectFlow.collectAsState(
                initial = WelcomeViewModel.SideEffect.Idle
            )

            LaunchedEffect(welcomeSideEffect) {
                when (welcomeSideEffect) {
                    WelcomeViewModel.SideEffect.NavigateToLogin ->
                        navController.navigateToLogin()
                    WelcomeViewModel.SideEffect.NavigateToSignUp ->
                        context.displayOptionNotAvailableToast()
                    else -> Unit
                }
            }

            WelcomePage(
                onSignUpClick = welcomeViewModel::onSignUpClick,
                onLoginClick = welcomeViewModel::onLoginClick,
            )
        }
        composable(NavItem.LoginNavItem.baseRoute) {
            val context = LocalContext.current

            val loginViewModel: LoginViewModel = hiltViewModel()

            val loginViewModelState by loginViewModel.container.stateFlow.collectAsState()

            val loginViewModelSideEffect by loginViewModel.container.sideEffectFlow.collectAsState(
                initial = LoginViewModel.SideEffect.Idle,
            )

            LaunchedEffect(loginViewModelSideEffect) {
                when (loginViewModelSideEffect) {
                    LoginViewModel.SideEffect.NavigateToForgotPassword -> context.displayOptionNotAvailableToast()
                    LoginViewModel.SideEffect.NavigateToHomePage -> TODO()
                    LoginViewModel.SideEffect.NavigateToWelcomePage -> navController.navigateToWelcome()
                    else -> Unit
                }
            }

            LoginPage(
                loginViewModelState = loginViewModelState,
                onForgotPasswordClick = loginViewModel::onForgotPasswordClick,
                onLoginClick = loginViewModel::onLoginClick,
                onEmailChange = loginViewModel::onEmailChange,
                onPasswordChange = loginViewModel::onPasswordChange,
                onBackClick = loginViewModel::onBackClick,
                onTogglePasswordVisibilityClick = loginViewModel::onTogglePasswordVisibility
            )
        }
    }
}

private fun NavHostController.navigateToLogin() {
    navigate(NavItem.LoginNavItem.baseRoute)
}

private fun NavHostController.navigateToWelcome() {
    navigate(NavItem.WelcomeNavItem.baseRoute)
}

private fun Context.displayOptionNotAvailableToast() {
    Toast.makeText(
        this,
        "¡Ups! Esta opción no se encuentra disponible",
        Toast.LENGTH_SHORT
    ).show()
}