package com.mupper.skeletonlogin.presentation.ui.page

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.mupper.skeletonlogin.R
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
    onBackClick: () -> Unit,
    onTogglePasswordVisibilityClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back Navigation")
                    }
                },
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
            )
        }
    ) {
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
                val isPasswordVisible = loginViewModelState.isPasswordVisible
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
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = onTogglePasswordVisibilityClick,
                            modifier = Modifier.size(24.dp),
                        ) {
                            Icon(
                                painterResource(
                                    if (isPasswordVisible) R.drawable.ic_visibility
                                    else R.drawable.ic_visibility_off
                                ),
                                contentDescription = "Visibility Icon"
                            )
                        }
                    },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(
                    onClick = onForgotPasswordClick,
                    modifier = Modifier
                        .align(Alignment.End)
                        .height(36.dp),
                ) {
                    Text("¿Olvidaste tu contraseña?", color = MaterialTheme.colors.primary)
                }
                Spacer(modifier = Modifier.weight(1f))
                val isLoading = loginViewModelState.isLoading
                val isLoginEnabled = loginViewModelState.isLoginEnabled
                Button(
                    onClick = onLoginClick,
                    shape = RoundedCornerShape(16.dp),
                    modifier = if (!isLoading) Modifier.fillMaxWidth()
                    else Modifier
                        .wrapContentWidth()
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally)
                        .height(36.dp),
                    enabled = isLoginEnabled,
                ) {
                    if (!loginViewModelState.isLoading) Text("Iniciar sesión")
                    else CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp),
                    )
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
            onBackClick = {},
            onTogglePasswordVisibilityClick = {},
        )
    }
}