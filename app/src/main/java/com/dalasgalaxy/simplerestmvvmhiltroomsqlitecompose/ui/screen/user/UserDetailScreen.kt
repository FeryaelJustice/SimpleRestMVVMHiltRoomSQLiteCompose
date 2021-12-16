package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.screen.user

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
fun UserDetail(navController: NavController, id: Int?, settingsViewModel: SettingsViewModel) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(1) {
            Button(onClick = {
                navController.navigate(Screen.UsersScreen.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }) {
                Text(text = "Back to main screen")
            }
            Text(text = "Id: $id")
        }
    }
}