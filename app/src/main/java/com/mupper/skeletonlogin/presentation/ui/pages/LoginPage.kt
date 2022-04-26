package com.mupper.skeletonlogin.presentation.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.mupper.skeletonlogin.presentation.ui.theme.SkeletonLoginTheme

@ExperimentalUnitApi
@Composable
fun LoginPage(
    onForgotPasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    onEmailChange: (TextFieldValue) -> Unit,
    onPasswordChange: (TextFieldValue) -> Unit,
) {
    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.Center)
            ) {
                Text("¡Hola de nuevo!", fontSize = TextUnit(32f, TextUnitType.Sp))
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = TextFieldValue(),
                    onValueChange = onEmailChange,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("Correo electrónico")
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = TextFieldValue(),
                    onValueChange = onPasswordChange,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("Contraseña")
                    }
                )
                TextButton(onClick = onForgotPasswordClick, modifier = Modifier.align(Alignment.End)) {
                    Text("¿Olvidaste tu contraseña?", color = MaterialTheme.colors.primary)
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = onLoginClick,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false,
                ) {
                    Text("Iniciar sesión")
                }
            }
        }
    }
}

@ExperimentalUnitApi
@Composable
@Preview
fun LoginPagePreview() {
    SkeletonLoginTheme {
        LoginPage(
            onForgotPasswordClick = {},
            onLoginClick = {},
            onEmailChange = {},
            onPasswordChange = {},
        )
    }
}