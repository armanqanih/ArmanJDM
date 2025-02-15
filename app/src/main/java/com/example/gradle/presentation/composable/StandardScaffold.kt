package com.example.gradle.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.LiveTv
import androidx.compose.material.icons.rounded.Public
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.gradle.ui.navigation.ScreenNavigation

@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    content: @Composable () -> Unit,

    ) {
    val items = listOf(
        BottomNavItem(
            title = "Home",
            route =
            ScreenNavigation.ExploreScreen.route,
            selectedItem = Icons.Filled.Home,
            unSelectedItem = Icons.Outlined.Home,
            ),

        BottomNavItem(
            title = "Popular",
            route = ScreenNavigation.ProfileScreen.route,
            selectedItem = Icons.Rounded.Public,
            unSelectedItem = Icons.Rounded.Public,

            ),


        BottomNavItem(
            title = "favorite",
            route = ScreenNavigation.CourseListScreen.route,
            selectedItem = Icons.Filled.Favorite,
            unSelectedItem = Icons.Outlined.FavoriteBorder,
            badgeCount = 2
        ),


        )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }




    Surface(modifier = modifier.fillMaxSize()) {

        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    NavigationBar(
                        containerColor = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .fillMaxWidth()

                    ) {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(

                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor =

                                        if (selectedItemIndex == index) MaterialTheme.colors.primary
                                        else MaterialTheme.colors.onBackground





                                ),
                                selected = selectedItemIndex == index,
                                onClick = {
                                    selectedItemIndex = index
                                    if (navController.currentDestination?.route != item.route) {
                                        navController.navigate(item.route)
                                    }
                                },
                                alwaysShowLabel = true,
                                icon = {
                                      Box(){
                                          BadgedBox(
                                              badge = {
                                                  if (item.badgeCount != null) {
                                                      Badge(
                                                          containerColor =  MaterialTheme.colors.onPrimary
                                                      ) {
                                                          androidx.compose.material3.Text(text = item.badgeCount.toString())
                                                      }
                                                  } else if (item.hasNews) {
                                                      Badge()
                                                  }
                                              }
                                          ) {
                                              Column(
                                                  horizontalAlignment = Alignment.CenterHorizontally,
                                                  verticalArrangement = Arrangement.Center,


                                                  ){
                                                  Icon(
                                                      modifier = Modifier.size(28.dp),
                                                      imageVector = if (selectedItemIndex == index) {
                                                          item.selectedItem
                                                      } else item.unSelectedItem,
                                                      tint = MaterialTheme.colors.onPrimary,
                                                      contentDescription = item.title,
                                                  )

                                                  Text(
                                                      modifier = Modifier.padding(start = 8.dp), // Add padding between icon and text
                                                      text = item.title,
                                                      color = MaterialTheme.colors.onPrimary,
                                                      style = MaterialTheme.typography.body2,
                                                      fontWeight = FontWeight.Bold
                                                  )

                                              }

                                          }
                                      }


                                },

                                )
                        }
                    }
                }

            }
        ) {
            Column(modifier = Modifier.padding(it)) {
                content()
            }

        }

    }

}


data class BottomNavItem(
    val title: String,
    val route: String,
    val selectedItem: ImageVector,
    val unSelectedItem: ImageVector,
    val hasNews: Boolean = false,
    val badgeCount: Int? = null

)