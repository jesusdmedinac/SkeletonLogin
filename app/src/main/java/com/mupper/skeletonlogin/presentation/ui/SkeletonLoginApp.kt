package com.mupper.skeletonlogin.presentation.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mupper.skeletonlogin.presentation.ui.navigation.NavItem
import com.mupper.skeletonlogin.presentation.ui.pages.LoginPage
import com.mupper.skeletonlogin.presentation.ui.pages.WelcomePage

@ExperimentalUnitApi
@Composable
fun SkeletonLoginApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavItem.WelcomeNavItem.baseRoute) {
        composable(NavItem.WelcomeNavItem.baseRoute) {
            val context = LocalContext.current

            WelcomePage(
                onSignUpClicked = {
                    Toast.makeText(
                        context,
                        "¡Ups! Esta opción no se encuentra disponible",
                        Toast.LENGTH_LONG
                    ).show()
                },
                onLoginClicked = {
                    navController.navigate(NavItem.LoginNavItem.baseRoute)
                },
            )
        }
        composable(NavItem.LoginNavItem.baseRoute) { LoginPage() }
    }
}
