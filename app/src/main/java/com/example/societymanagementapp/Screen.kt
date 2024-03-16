package com.example.societymanagementapp

sealed class Screen(val route :String) {//all screens route are defined here
object SignInScreen : Screen("signinScreen")
    object ProfileScreen : Screen("homeScreen")
    object VisitorScreen : Screen("visitorsScreen")
    object ComplaintBoxScreen : Screen("ComplaintBoxScreen")
}