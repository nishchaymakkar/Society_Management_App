package com.example.societymanagementapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun loginPage() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.White),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sign in",
                    color = Color.Blue,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                )
            }

                var username by remember {
                    mutableStateOf("")
                }
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    maxLines = 1,
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        color = Color.Black,
                        ),

                    label = {
                        Text(
                            text = "Username"
                        )

                    },
                    leadingIcon = {
                        Image(
                            painterResource(id = R.drawable.person), contentDescription = "person"
                        )
                    }
                )
            Spacer(modifier = Modifier.height(10.dp))
                var password by remember {
                    mutableStateOf("")
                }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    maxLines = 1,
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        color = Color.Black,
                    ),
                    label = {
                        Text(
                            text = "Password"
                        )

                    },
                    leadingIcon = {
                        Image(
                            painterResource(id = R.drawable.password), contentDescription = "person"
                        )
                    }

                )
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedButton(onClick = { /*TODO*/ },
                border= BorderStroke(1.dp, Color.Blue),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Blue,
                )
            ) {
                Text(text ="Login")
            }
            Spacer(modifier = Modifier.height(5.dp))
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "forgot password?",
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    )
            }
            Spacer(modifier = Modifier.height(70.dp))
            Text(text = "or login with",
                color = Color.Black,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                )
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = {/*todo*/},
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent
                )
            ) {
                Image(
                    painterResource(id = R.drawable.googleicon),
                    contentDescription = "sign in logo "
                )
            }
        }
    }
}


