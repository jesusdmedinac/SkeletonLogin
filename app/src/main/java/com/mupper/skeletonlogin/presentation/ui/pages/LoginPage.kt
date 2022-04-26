package com.mupper.skeletonlogin.presentation.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoginPage() {
    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            Text("Login Page", modifier = Modifier.align(Alignment.Center))
        }
    }
}