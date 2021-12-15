package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.navigation.Screen
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.viewmodel.SettingsViewModel

@Composable
fun RegisterScreen(navController: NavController, settingsViewModel: SettingsViewModel) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(1) {
            Button(onClick = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }) {
                Text(text = "Go to login screen")
            }
            Button(onClick = {
                navController.navigate(Screen.ForgotPassword.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }) {
                Text(text = "Go to forgot password screen")
            }
            Button(onClick = {
                navController.navigate(Screen.UsersScreen.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
                settingsViewModel.setIsLogged(true)
            }) {
                Text(text = "Go to user screen")
            }
        }
    }
}