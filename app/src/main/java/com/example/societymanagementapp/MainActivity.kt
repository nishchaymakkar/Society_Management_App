package com.example.societymanagementapp

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Identity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.societymanagementapp.googleSignIn.GoogleAuthUiClient
import com.example.societymanagementapp.googleSignIn.SignInScreen
import com.example.societymanagementapp.googleSignIn.SignInViewModel
import com.example.societymanagementapp.ui.theme.SocietyManagementAppTheme
import kotlinx.coroutines.launch
import kotlin.contracts.contract

class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy{
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocietyManagementAppTheme {
                loginPage()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "sign_in"){
                    composable("sign_in"){
                        val ViewModel = viewModels<SignInViewModel> ()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        val launcher = rememmberLauncherforActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult,
                            onResult ={
                                result ->
                                if(result.resultCode == RESULT_OK){
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.signInWithIntent(
                                            intent = result.data?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )
                        SignInScreen(
                            state = state,
                            onSignInClick = (
                                    lifecycleScope.launch {
                                        val SignInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch()
                                        IntentSenderRequest.Builder(
                                            signInIntetSender?: return@launch
                                        ).build
                                    }
                                    )
                        )
                    }
                }
            }
        }
    }
}



