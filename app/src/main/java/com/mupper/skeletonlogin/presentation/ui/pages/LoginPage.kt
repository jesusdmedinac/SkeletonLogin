package com.mupper.skeletonlogin.presentation.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.mupper.skeletonlogin.presentation.ui.theme.SkeletonLoginTheme
import com.mupper.skeletonlogin.presentation.viewmodel.LoginViewModel

@ExperimentalUnitApi
@Composable
fun LoginPage(
    loginViewModelState: LoginViewModel.State,
    onForgotPasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
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

                val email = loginViewModelState.email
                var emailTextFieldValue by remember { mutableStateOf(TextFieldValue(email)) }
                TextField(
                    value = emailTextFieldValue,
                    onValueChange = {
                        emailTextFieldValue = it
                        onEmailChange(emailTextFieldValue.text)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("Correo electrónico")
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))

                val password = loginViewModelState.password
                var passwordTextFieldValue by remember { mutableStateOf(TextFieldValue(password)) }
                TextField(
                    value = passwordTextFieldValue,
                    onValueChange = {
                        passwordTextFieldValue = it
                        onPasswordChange(passwordTextFieldValue.text)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("Contraseña")
                    }
                )
                TextButton(
                    onClick = onForgotPasswordClick,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("¿Olvidaste tu contraseña?", color = MaterialTheme.colors.primary)
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = onLoginClick,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = loginViewModelState.isLoginEnabled,
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
            loginViewModelState = LoginViewModel.State(),
            onForgotPasswordClick = {},
            onLoginClick = {},
            onEmailChange = {},
            onPasswordChange = {},
        )
    }
}