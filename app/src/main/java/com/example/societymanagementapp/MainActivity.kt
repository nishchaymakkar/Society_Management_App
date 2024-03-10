@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.societymanagementapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
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
import com.example.societymanagementapp.visitorsScreenAndData.ExpandableCard

import com.example.societymanagementapp.visitorsScreenAndData.VisitorScreen
import com.example.societymanagementapp.visitorsScreenAndData.VisitorViewModel
import com.example.societymanagementapp.visitorsScreenAndData.Visitors
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import androidx.compose.runtime.LaunchedEffect as LaunchedEffect1


@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = com.google.android.gms.auth.api.identity.Identity.getSignInClient(
                applicationContext
            )
        )
    }



    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocietyManagementAppTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "sign_in") {
                    composable(route = "sign_in") {
                        val viewModel = viewModel<SignInViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect1(key1 = Unit) {
                            if (googleAuthUiClient.getSignedInUser() != null) {
                                navController.navigate("profile")
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
                        LaunchedEffect1(key1 = state.isSignInSuccessful) {
                            if (state.isSignInSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    "Sign in successful",
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
                        route = "profile"
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
                                navController.navigate("visitor")
                            }, onComplaintClick = {
                                navController.navigate("complaints")
                            },
                            viewModel = ProfileViewModel()
                        )
                    }

                    composable(
                        route = "visitor"
                    ) {
                        VisitorScreen( )
                        var visitorList = mutableStateListOf<Visitors?>()


                        var db = FirebaseFirestore.getInstance()
                        var visitors = Visitors()

                        db.collection("visitors").get()
                            .addOnSuccessListener { queryDocumentSnapshots ->
                                // after getting the data we are calling
                                // on success method
                                // and inside this method we are checking
                                // if the received query snapshot is empty or not.
                                if (!queryDocumentSnapshots.isEmpty) {
                                    // if the snapshot is not empty we are
                                    // hiding our progress bar and adding
                                    // our data in a list.
                                    // loadingPB.setVisibility(View.GONE)
                                    val list = queryDocumentSnapshots.documents
                                    for (d in list) {
                                        // after getting this list we are passing that
                                        // list to our object class.
                                        val c: Visitors? = d.toObject(Visitors::class.java)
                                        // and we will pass this object class inside
                                        // our arraylist which we have created for list view.
                                        visitorList.add(c)

                                    }
                                } else {
                                    // if the snapshot is empty we are displaying
                                    // a toast message.
                                    Toast.makeText(
                                        applicationContext,
                                        "No data found in Database",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            // if we don't get any data or any error
                            // we are displaying a toast message
                            // that we do not get any data
                            .addOnFailureListener {
                                Toast.makeText(
                                    applicationContext,
                                    "Fail to get the data.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        ExpandableCard(visitorList)

                    }
                    composable(
                        route = "complaints"
                    ) {
                        ComplaintBoxScreen(viewModel = ComplaintScreenViewModel())

                    }

                }


            }
        }
    }
}



