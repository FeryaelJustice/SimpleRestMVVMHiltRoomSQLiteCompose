package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.navigation

import androidx.annotation.StringRes
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.R

// Nested
const val main = "main"
const val users = "users"

sealed class Screen(val route: String, @StringRes val resourceId: Int){

    // Main
    object Login: Screen("login", R.string.login)
    object Register: Screen("register",R.string.register)
    object ForgotPassword: Screen("forgot_password",R.string.forgot_password)
    object AppActivity: Screen("app",R.string.app)

    // AppActivity
    // Users
    object UsersScreen: Screen("user_list",R.string.user_list)
    object UserDetailScreen: Screen("user_detail",R.string.user_detail)

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
