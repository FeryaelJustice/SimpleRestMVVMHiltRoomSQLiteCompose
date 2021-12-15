package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.annotation.ExperimentalCoilApi
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.user.User
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.navigation.Navigation
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.screen.MainActivity
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.screen.user.MyApp
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.viewmodel.SettingsViewModel
import dagger.hilt.android.testing.HiltAndroidRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun addingItemsWorksCorrectly() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: SettingsViewModel = hiltViewModel()
            Navigation(navController, viewModel)
        }
        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithText("Name 0 LastName 0").assertExists()

        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithText("Name 1 LastName 1").assertExists()
    }

    @Test
    fun removingItemsWorksCorrectly() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: SettingsViewModel = hiltViewModel()
            Navigation(navController, viewModel)
        }
        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithText("Name 0 LastName 0").assertExists()

        composeTestRule.onNodeWithContentDescription("Remove").performClick()
        composeTestRule.onNodeWithText("Name 0 LastName 0").assertDoesNotExist()
    }

    @ExperimentalCoilApi
    @Test
    fun loadingIsVisibleWhenAddingItems() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            MyApp(
                users = emptyList(), isLoading = true, navController = navController
            )
        }
        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithText("loadingCard").assertExists()
    }
}