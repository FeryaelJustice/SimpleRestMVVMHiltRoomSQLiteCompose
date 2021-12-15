package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.navigation.*
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.theme.SimpleRestMVVMHiltRoomSQLiteComposeTheme
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleRestMVVMHiltRoomSQLiteComposeTheme {
                val navController = rememberNavController()
                val viewModel: SettingsViewModel = hiltViewModel()
                Navigation(navController, viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleRestMVVMHiltRoomSQLiteComposeTheme {
        val navController = rememberNavController()
        Navigation(navController, hiltViewModel())
    }
}

// Erase backstack
/*
navController.navigate(Screen.Login.route){
    popUpTo(navController.graph.startDestinationId)
    launchSingleTop = true
}
*/