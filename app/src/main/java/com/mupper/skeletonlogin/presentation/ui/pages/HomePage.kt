package com.mupper.skeletonlogin.presentation.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.mupper.skeletonlogin.R
import com.mupper.skeletonlogin.presentation.ui.theme.SkeletonLoginTheme

@ExperimentalUnitApi
@Composable
fun HomePage(
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                title = {},
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                "Welcome To ${stringResource(R.string.app_name)}",
                fontSize = TextUnit(28f, TextUnitType.Sp),
                modifier = Modifier.align(Alignment.Center)
            )

            Text("Developed by @JesusDMedinaC", modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@ExperimentalUnitApi
@Composable
@Preview
fun HomePagePreview() {
    SkeletonLoginTheme {
        HomePage(
            onBackClick = {}
        )
    }
}