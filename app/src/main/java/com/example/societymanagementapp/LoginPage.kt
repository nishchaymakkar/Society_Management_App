package com.example.societymanagementapp

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun loginPage() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    ){
        Column(modifier = Modifier.fillMaxSize()) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                //for username
                var username by remember {
                    mutableStateOf("" )
                }
                TextField(
                    value = username,
                    onValueChange ={ username = it },
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        color = Color.Black,
                        background = Color.White,
                    ),

                    placeholder = {
                        Text(text = "Username",
                            color = Color.DarkGray,
                            modifier = Modifier.background(Color.White),
                        )

                    },
                    leadingIcon = {
                        Image(painterResource(id = R.drawable.person)  , contentDescription ="person",
                            Modifier.background(Color.White))
                    },
                )
                //for password
                var password by remember {
                    mutableStateOf("" )
                }
                TextField(
                    value = password,
                    onValueChange ={ password = it },
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        color = Color.Black,
                        background = Color.White,
                    ),

                    placeholder = {
                        Text(text = "password",
                            color = Color.DarkGray,
                            modifier = Modifier.background(Color.White),
                        )

                    },
                    leadingIcon = {
                        Image(painterResource(id = R.drawable.password)  , contentDescription ="password",
                            Modifier.background(Color.White))
                    },
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "or sign in with",
                    fontSize = 20.sp)
                Button(
                    modifier = Modifier.size(100.dp),
                    onClick = { /* todo*/ },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Transparent
                    )
                ) {
                    Image(painterResource(id = R.drawable.googleicon), contentDescription = "sign in logo")
                }
            }
        }
    }
}


