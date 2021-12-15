package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.view.App
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.view.MainActivity
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.view.user.MyApp
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
            App()
        }
        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithText("Name 0 LastName 0").assertExists()

        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithText("Name 1 LastName 1").assertExists()
    }

    @Test
    fun removingItemsWorksCorrectly() {
        composeTestRule.setContent {
            App()
        }
        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithText("Name 0 LastName 0").assertExists()

        composeTestRule.onNodeWithContentDescription("Remove").performClick()
        composeTestRule.onNodeWithText("Name 0 LastName 0").assertDoesNotExist()
    }

    @Test
    fun loadingIsVisibleWhenAddingItems() {
        composeTestRule.setContent {
            MyApp(users = emptyList(), isLoading = true)
        }
        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithText("loadingCard").assertExists()
    }
}