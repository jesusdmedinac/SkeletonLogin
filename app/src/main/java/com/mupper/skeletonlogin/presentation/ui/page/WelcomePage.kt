package com.mupper.skeletonlogin.presentation.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.mupper.skeletonlogin.R
import com.mupper.skeletonlogin.presentation.ui.theme.SkeletonLoginTheme

@ExperimentalUnitApi
@Composable
fun WelcomePage(
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(52.dp))
            Image(
                painterResource(R.drawable.ic_launcher),
                contentDescription = "",
            )
            Text(stringResource(R.string.app_name), fontSize = TextUnit(28f, TextUnitType.Sp))
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onSignUpClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("CREAR CUENTA")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("¿Ya tienes una cuenta?")
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick = onLoginClick, modifier = Modifier.height(36.dp)) {
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
            onSignUpClick = {},
            onLoginClick = {},
        )
    }
}