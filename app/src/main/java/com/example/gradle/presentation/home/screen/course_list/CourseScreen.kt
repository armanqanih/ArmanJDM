package com.example.gradle.presentation.home.screen.course_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gradle.presentation.home.screen.course_list.compose.CourseItem
import com.example.gradle.presentation.util.Dimens.SpaceSmall
import com.example.gradle.ui.navigation.ScreenNavigation

@Composable
fun CourseScreen(
   onNavigateToDetailScreen: (String) -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        // Title and filter button row
        Spacer(modifier = Modifier.height(35.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(SpaceSmall),
        ) {
            items(10) {
                CourseItem(onItemClick = {
//              onNavigateToDetailScreen()
                })

            }
        }
    }
}

