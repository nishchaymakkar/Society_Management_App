package com.example.societymanagementapp

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Identity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.societymanagementapp.googleSignIn.GoogleAuthUiClient
import com.example.societymanagementapp.googleSignIn.ProfileScreen
import com.example.societymanagementapp.googleSignIn.SignInScreen
import com.example.societymanagementapp.googleSignIn.SignInViewModel
import com.example.societymanagementapp.ui.theme.SocietyManagementAppTheme
import kotlinx.coroutines.launch
import kotlin.contracts.contract


class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = com.google.android.gms.auth.api.identity.Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocietyManagementAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  val navController = rememberNavController()
                  NavHost(navController = navController, startDestination = "sign_in" ){
                      composable("sign_in"){
                          val viewModel = viewModel<SignInViewModel>()
                          val state by viewModel.state.collectAsStateWithLifecycle()

                          LaunchedEffect(key1 = Unit ){
                              if(googleAuthUiClient.getSignedInUser() != null){
                                  navController.navigate("profile")
                              }
                          }

                          val launcher = rememberLauncherForActivityResult(
                              contract = ActivityResultContracts.StartIntentSenderForResult(),
                              onResult = {result->
                                  if (result.resultCode == RESULT_OK){
                                      lifecycleScope.launch {
                                          val signInResult = googleAuthUiClient.getSignWithIntent(
                                              intent = result.data ?: return@launch
                                          )
                                          viewModel.onSignInResult(signInResult)
                                      }
                                  }
                              }
                          )
                          LaunchedEffect(key1 = state.isSignInSuccessful){
                              if(state.isSignInSuccessful){
                                  Toast.makeText(
                                      applicationContext,
                                      "Sign in succesfull",
                                      Toast.LENGTH_LONG
                                  ).show()
                                  navController.navigate("profile")
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
                          route ="profile"
                      )    {
                          ProfileScreen(userData = googleAuthUiClient.getSignedInUser(),
                              onSignOut = {
                                  lifecycleScope.launch{
                                      googleAuthUiClient.signOut()
                                      Toast.makeText(applicationContext,"Sign Out",Toast.LENGTH_LONG).show()

                                      navController.popBackStack()
                                  }
                              })
                      }

                    }
                }
            }
        }
    }
}



