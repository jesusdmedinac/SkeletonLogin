package com.mupper.skeletonlogin.presentation.ui.pages

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.mupper.skeletonlogin.R
import com.mupper.skeletonlogin.presentation.ui.theme.SkeletonLoginTheme

@ExperimentalUnitApi
@Composable
fun WelcomePage(
    onSignUpClicked: () -> Unit,
    onLoginClicked: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.app_name), fontSize = TextUnit(28f, TextUnitType.Sp))
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onSignUpClicked,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("CREAR CUENTA")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("¿Ya tienes una cuenta?")
                TextButton(onClick = onLoginClicked) {
                    Text("Inicia Sesión")
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@ExperimentalUnitApi
@Composable
@Preview
fun WelcomePagePreview() {
    SkeletonLoginTheme {
        WelcomePage(
            onSignUpClicked = {},
            onLoginClicked = {},
        )
    }
}