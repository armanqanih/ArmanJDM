package com.example.gradle.presentation.auth.screen.login.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gradle.R
import com.example.gradle.ui.theme.LotkaTheme


@Composable
fun Background() {
    val screenHeight = getScreenHeight()
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight.div(2).dp)
                .background(color = LotkaTheme.color.primary)
        ){
            Image(
                painter = painterResource(id = R.drawable.group_6),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxSize()
            )
            Image(
                painter = painterResource(id = R.drawable.group_11),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(screenHeight.div(2).dp)
                .background(color = LotkaTheme.color.background)
        )
    }

}