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
import com.mupper.skeletonlogin.presentation.ui.page.HomePage
import com.mupper.skeletonlogin.presentation.ui.page.LoginPage
import com.mupper.skeletonlogin.presentation.ui.page.WelcomePage
import com.mupper.skeletonlogin.presentation.viewmodel.HomeViewModel
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
                    LoginViewModel.SideEffect.NavigateToHomePage -> navController.navigateToHome()
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

        composable(NavItem.HomeNavItem.baseRoute) {
            val homeViewModel: HomeViewModel = hiltViewModel()

            val homeViewModelState by homeViewModel.container.stateFlow.collectAsState()

            val homeViewModelSideEffect by homeViewModel.container.sideEffectFlow.collectAsState(
                initial = HomeViewModel.SideEffect.Idle
            )

            LaunchedEffect(homeViewModelSideEffect) {
                when (homeViewModelSideEffect) {
                    HomeViewModel.SideEffect.LogOut -> navController.logOut()
                    else -> Unit
                }
            }

            HomePage(
                homeViewModelState,
                onBackClick = homeViewModel::onBackClick,
                onLogOutDialogDismissClick = homeViewModel::onLogOutDialogDismissClick,
                onLogOutClick = homeViewModel::onLogOutClick,
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

private fun NavHostController.navigateToHome() {
    navigate(NavItem.HomeNavItem.baseRoute) {
        popUpTo(NavItem.LoginNavItem.baseRoute) {
            inclusive = true
        }
        popUpTo(NavItem.WelcomeNavItem.baseRoute) {
            inclusive = true
        }
    }
}

private fun NavHostController.logOut() {
    navigate(NavItem.WelcomeNavItem.baseRoute) {
        popUpTo(NavItem.HomeNavItem.baseRoute) {
            inclusive = true
        }
    }
}

private fun Context.displayOptionNotAvailableToast() {
    Toast.makeText(
        this,
        "??Ups! Esta opci??n no se encuentra disponible",
        Toast.LENGTH_SHORT
    ).show()
}