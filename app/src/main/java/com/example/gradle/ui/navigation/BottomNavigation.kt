package com.example.gradle.ui.navigation

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.gradle.ui.theme.LotkaTheme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavigation(
    navController: NavHostController,
    bottomNavItems: List<BottomNavItem>
) {


    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route  // Reactive tracking







    NavigationBar(
        containerColor = LotkaTheme.color.surface,
        contentColor = LotkaTheme.color.primary,
        modifier = Modifier.fillMaxWidth(),


    ) {
        bottomNavItems.forEachIndexed { index, item ->
            val isSelected = currentRoute == item.route::class.qualifiedName
            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor =  LotkaTheme.color.textPrimary,
                    selectedTextColor = LotkaTheme.color.textPrimary,
                    unselectedIconColor = LotkaTheme.color.textSecondary,
                    unselectedTextColor = LotkaTheme.color.textSecondary,
                    selectedIndicatorColor = LotkaTheme.color.primary,
                    disabledIconColor = LotkaTheme.color.textSecondary,
                    disabledTextColor = LotkaTheme.color.textSecondary,
                ),
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route as Any)
                },
                alwaysShowLabel = true,
                icon = {
                        Icon(
                            painter = painterResource( item.selectedIcon),
                            contentDescription = "we",
                            modifier = Modifier.padding(
                                if(isSelected){
                                    5.dp
                                }else{
                                    0.dp
                                }
                            )
                        )
                },
                interactionSource = MutableInteractionSource(),
                label = {
                    Text(
                        text = stringResource(item.title),
                        color = LotkaTheme.color.textSecondary,
                        style = LotkaTheme.typography.contentText.copy(fontSize = 12.sp),
                    )
                },


            )


        }





}}


