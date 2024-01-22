package com.example.societymanagementapp.googleSignIn

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
