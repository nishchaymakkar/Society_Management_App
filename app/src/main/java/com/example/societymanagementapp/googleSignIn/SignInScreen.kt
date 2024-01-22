package com.example.societymanagementapp.googleSignIn

import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: Job
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let { error ->
            Toast.makeText(
                context,error,Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ){
        Button(onClick = onSignInClick) {
            Text(text ="Sign In")
        }
    }
}