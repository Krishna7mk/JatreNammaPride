package com.example.jatrenammapride.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jatrenammapride.screens.admin.AdminLoginScreen
import com.example.jatrenammapride.screens.admin.AdminLostFoundScreen
import com.example.jatrenammapride.screens.admin.AdminPanelScreen
import com.example.jatrenammapride.screens.ai.AiScreen
import com.example.jatrenammapride.screens.home.HomeScreen
import com.example.jatrenammapride.screens.lostfound.LostFoundScreen
import com.example.jatrenammapride.screens.maps.MapScreen
import com.example.jatrenammapride.screens.safety.SafetyScreen
import com.example.jatrenammapride.screens.schedule.ScheduleScreen
import com.example.jatrenammapride.navigation.Screen


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val navBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry?.destination?.route

    Scaffold(

        bottomBar = {

            NavigationBar {

                NavigationBarItem(

                    selected = currentRoute == Screen.Home.route,

                    onClick = {
                        navController.navigate(Screen.Home.route)
                    },

                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    },

                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(

                    selected = currentRoute == Screen.Schedule.route,

                    onClick = {
                        navController.navigate(Screen.Schedule.route)
                    },

                    icon = {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = "Schedule"
                        )
                    },

                    label = {
                        Text("Schedule")
                    }
                )

                NavigationBarItem(

                    selected = currentRoute == Screen.Map.route,

                    onClick = {
                        navController.navigate(Screen.Map.route)
                    },

                    icon = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Map"
                        )
                    },

                    label = {
                        Text("Map")
                    }
                )

                NavigationBarItem(

                    selected = currentRoute == Screen.LostFound.route,

                    onClick = {
                        navController.navigate(Screen.LostFound.route)
                    },

                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "LostFound"
                        )
                    },

                    label = {
                        Text("Lost")
                    }
                )

                NavigationBarItem(

                    selected = currentRoute == Screen.Safety.route,

                    onClick = {
                        navController.navigate(Screen.Safety.route)
                    },

                    icon = {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = "Safety"
                        )
                    },

                    label = {
                        Text("Safety")
                    }
                )

                NavigationBarItem(

                    selected = currentRoute == Screen.Ai.route,

                    onClick = {
                        navController.navigate(Screen.Ai.route)
                    },

                    icon = {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "AI"
                        )
                    },

                    label = {
                        Text("AI")
                    }
                )

                NavigationBarItem(

                    selected = currentRoute == Screen.Admin.route,

                    onClick = {
                        navController.navigate(Screen.Admin.route)
                    },

                    icon = {
                        Icon(
                            imageVector =
                                Icons.Default.AdminPanelSettings,
                            contentDescription = "Admin"
                        )
                    },

                    label = {
                        Text("Admin")
                    }
                )
            }
        }

    ) { paddingValues ->

        NavHost(

            navController = navController,

            startDestination = Screen.Home.route,

            modifier = Modifier.padding(paddingValues)
        ) {

            composable(Screen.Home.route) {

                HomeScreen()
            }

            composable(Screen.Schedule.route) {

                ScheduleScreen(navController)
            }

            composable(Screen.Map.route) {

                MapScreen(navController)
            }

            composable(Screen.LostFound.route) {

                LostFoundScreen(navController)
            }

            composable(Screen.Safety.route) {

                SafetyScreen(navController)
            }

            composable(Screen.Ai.route) {

                AiScreen(navController)
            }

            composable(Screen.Admin.route) {

                AdminLoginScreen(navController)
            }

            composable(Screen.AdminPanel.route) {

                AdminPanelScreen(navController)
            }

            composable(Screen.AdminLostFound.route) {

                AdminLostFoundScreen(navController)
            }
        }
    }
}