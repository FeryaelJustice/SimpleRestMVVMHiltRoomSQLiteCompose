package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import androidx.navigation.compose.navigation
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.screen.main.ForgotPasswordScreen
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.screen.main.LoginScreen
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.screen.main.RegisterScreen
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.screen.user.UserDetail
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.screen.user.Users
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.viewmodel.SettingsViewModel

@Composable
fun Navigation(navController: NavHostController, settingsViewModel: SettingsViewModel) {
    val appScreens = listOf(
        Screen.UsersScreen,
        Screen.UserDetailScreen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if ((currentDestination?.route == Screen.Login.route) || (currentDestination?.route == Screen.Register.route) || (currentDestination?.route == Screen.ForgotPassword.route)) {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = "App by Fer") },
                navigationIcon = {
                    IconButton(onClick = {
                        settingsViewModel.setIsLogged(false)
                        navController.navigate(Screen.Login.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                elevation = 12.dp,
                actions = {}
            )
        }) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = main,
                modifier = Modifier.padding(innerPadding)
            ) {
                graph(navController = navController, settingsViewModel)
            }
        }
    } else {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = "App by Fer") },
                navigationIcon = {
                    IconButton(onClick = {
                        settingsViewModel.setIsLogged(false)
                        navController.navigate(Screen.Login.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                elevation = 12.dp,
                actions = {}
            )
        }, bottomBar = {
            BottomNavigation {
                appScreens.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = main,
                modifier = Modifier.padding(innerPadding)
            ) {
                graph(navController = navController, settingsViewModel)
            }
        }
    }
}


fun NavGraphBuilder.graph(navController: NavHostController, settingsViewModel: SettingsViewModel) {
    navigation(startDestination = Screen.Login.route, route = main) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController, settingsViewModel)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController, settingsViewModel)
        }
        composable(route = Screen.ForgotPassword.route) {
            ForgotPasswordScreen(navController = navController, settingsViewModel)
        }
    }
    navigation(startDestination = Screen.UsersScreen.route, route = users) {
        composable(route = Screen.UsersScreen.route) {
            Users(navController = navController, settingsViewModel = settingsViewModel)
        }
        composable(route = Screen.UserDetailScreen.route) {
            UserDetail(navController = navController, id = 0, settingsViewModel)
        }
        composable(
            route = Screen.UserDetailScreen.route + "/{id}", arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { entry ->
            UserDetail(
                navController = navController,
                id = entry.arguments?.getInt("id"),
                settingsViewModel
            )
        }
    }
}