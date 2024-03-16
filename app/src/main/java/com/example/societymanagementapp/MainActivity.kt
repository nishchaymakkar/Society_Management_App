@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.societymanagementapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.societymanagementapp.complaintScreen.ComplaintBoxScreen
import com.example.societymanagementapp.complaintScreen.ComplaintScreenViewModel
import com.example.societymanagementapp.googleSignIn.GoogleAuthUiClient
import com.example.societymanagementapp.googleSignIn.ProfileScreen
import com.example.societymanagementapp.googleSignIn.ProfileViewModel
import com.example.societymanagementapp.googleSignIn.SignInScreen
import com.example.societymanagementapp.googleSignIn.SignInViewModel
import com.example.societymanagementapp.ui.theme.SocietyManagementAppTheme
import com.example.societymanagementapp.visitorsScreenAndData.VisitorScreen
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch


@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(
                applicationContext
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocietyManagementAppTheme {
                val navController = rememberNavController()
                
                NavHost(navController = navController, startDestination = Screen.SignInScreen.route) {
                    composable(route = Screen.SignInScreen.route) {
                        val viewModel = viewModel<SignInViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = Unit) {
                            if (googleAuthUiClient.getSignedInUser() != null) {
                                navController.navigate(Screen.ProfileScreen.route)
                            }
                        }

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.getSignWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )
                        LaunchedEffect(key1 = state.isSignInSuccessful) {
                            if (state.isSignInSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    "Sign in successful",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(Screen.ProfileScreen.route)
                                viewModel.resetState()
                            }
                        }
                        SignInScreen(
                            state = state,
                            onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            }
                        )
                    }
                    composable(
                        route = Screen.ProfileScreen.route
                    ) {
                        ProfileScreen(userData = googleAuthUiClient.getSignedInUser(),
                            onSignOut = {
                                lifecycleScope.launch {
                                    googleAuthUiClient.signOut()
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign Out",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.popBackStack()
                                }
                            }, onVisitorClick = {
                                navController.navigate(Screen.VisitorScreen.route)
                            }, onComplaintClick = {
                                navController.navigate(Screen.ComplaintBoxScreen.route)
                            },
                            viewModel = ProfileViewModel()
                        )
                    }

                    composable(
                        Screen.VisitorScreen.route
                    ) {
                        VisitorScreen( )

                    }
                    composable(
                        Screen.ComplaintBoxScreen.route
                    ) {
                        ComplaintBoxScreen(viewModel = ComplaintScreenViewModel())

                    }

                }
            }
        }
    }
}



