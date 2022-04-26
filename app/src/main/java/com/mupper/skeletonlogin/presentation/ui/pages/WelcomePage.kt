package com.mupper.skeletonlogin.presentation.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mupper.skeletonlogin.presentation.ui.navigation.NavItem

@Composable
fun WelcomePage(
    onLoginClicked: () -> Unit,
) {
    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            Button(onClick = onLoginClicked, modifier = Modifier.align(Alignment.Center)) {
                Text("Go to ${NavItem.LoginNavItem.baseRoute}")
            }
        }
    }
}