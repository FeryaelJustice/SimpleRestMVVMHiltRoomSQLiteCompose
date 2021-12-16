package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.navigation

import androidx.annotation.StringRes
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.R

// Nested
const val main = "main"
const val users = "users"

// Screens
const val splash = "splash"
const val login = "login"
const val register = "register"
const val forgot_password = "forgot_password"
const val user_list = "user_list"
const val user_detail = "user_detail"

sealed class Screen(val route: String, @StringRes val resourceId: Int) {

    // Main
    object Splash : Screen(splash, R.string.splash)
    object Login : Screen(login, R.string.login)
    object Register : Screen(register, R.string.register)
    object ForgotPassword : Screen(forgot_password, R.string.forgot_password)

    // Users
    object UsersScreen : Screen(user_list, R.string.user_list)
    object UserDetailScreen : Screen(user_detail, R.string.user_detail)

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
