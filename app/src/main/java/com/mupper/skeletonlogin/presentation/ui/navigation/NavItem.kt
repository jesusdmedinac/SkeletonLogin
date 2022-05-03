package com.mupper.skeletonlogin.presentation.ui.navigation

sealed class NavItem(
    val baseRoute: String,
) {
    object WelcomeNavItem : NavItem("welcome-page")
    object LoginNavItem : NavItem("login-page")
    object HomeNavItem : NavItem("home-page")
}
