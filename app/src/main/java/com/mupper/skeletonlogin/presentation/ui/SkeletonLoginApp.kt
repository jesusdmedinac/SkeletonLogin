package com.mupper.skeletonlogin.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mupper.skeletonlogin.presentation.ui.navigation.NavItem
import com.mupper.skeletonlogin.presentation.ui.pages.LoginPage
import com.mupper.skeletonlogin.presentation.ui.pages.WelcomePage

@Composable
fun SkeletonLoginApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavItem.WelcomeNavItem.baseRoute) {
        composable(NavItem.WelcomeNavItem.baseRoute) {
            WelcomePage {
                navController.navigate(NavItem.LoginNavItem.baseRoute)
            }
        }
        composable(NavItem.LoginNavItem.baseRoute) { LoginPage() }
    }
}
