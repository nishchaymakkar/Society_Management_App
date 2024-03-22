@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.societymanagementapp.googleSignIn

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.societymanagementapp.DialogViewModel
import com.example.societymanagementapp.R
import kotlin.coroutines.coroutineContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOut: () -> Unit,
    onVisitorClick: () -> Unit,
    onComplaintClick: () -> Unit,
    viewModel: DialogViewModel
) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column(modifier= Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
            //first row for profilephoto and application logo
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier= Modifier.fillMaxWidth()) {
                Row(
                    modifier =Modifier.padding(20.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Image(
                        painterResource(id = R.drawable.logo), contentDescription = "Society logo",
                        Modifier.size(50.dp))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.End
                ) {

                    if(userData?.profilePictureUrl != null){
                        IconButton(onClick = {viewModel.onOKayClick() }) {
                            AsyncImage(model = userData.profilePictureUrl, contentDescription ="profilePicture",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                if (userData?.username != null){
                    Text(text = "Welcome, "+userData.username,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Apartment Address",fontSize = 15.sp, color = Color.DarkGray)
            }
            Spacer(modifier = Modifier.height(20.dp))

            Column {
                val context = LocalContext.current
                Row(modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        onClick = onVisitorClick,
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        )

                    )
                    {
                        Text(
                            text = "Visitors",
                            modifier = Modifier.padding(10.dp),
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Image(
                            painterResource(id = R.drawable.people),
                            contentDescription = "visitors logo",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(horizontal = 10.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Image(
                                painterResource(id = R.drawable.arrow),
                                contentDescription = "forward arrow",
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(5.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ), onClick = onComplaintClick,
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        )
                    )
                    {
                        Text(
                            text = "Complaints",
                            modifier = Modifier.padding(10.dp),
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Image(
                            painterResource(id = R.drawable.complainticon),
                            contentDescription = "complaints logo",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(horizontal = 10.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Image(
                                painterResource(id = R.drawable.arrow),
                                contentDescription = "forward arrow" ,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(5.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
                Row(modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        onClick ={ Toast.makeText(context, "coming soon", Toast.LENGTH_LONG
                        ).show()},
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        )

                    )
                    {
                        Text(
                            text = "Maintenance",
                            modifier = Modifier.padding(10.dp),
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Image(
                            painterResource(id = R.drawable.maintaince),
                            contentDescription = "maintenance logo",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(horizontal = 10.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Image(
                                painterResource(id = R.drawable.arrow),
                                contentDescription = "forward arrow",
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(5.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ), onClick = { Toast.makeText(context, "coming soon", Toast.LENGTH_LONG
                        ).show()},
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        )
                    )
                    {
                        Text(
                            text = "Meetings",
                            modifier = Modifier.padding(10.dp),
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Image(
                            painterResource(id = R.drawable.meetings),
                            contentDescription = "Meetings logo",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(horizontal = 10.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Image(
                                painterResource(id = R.drawable.arrow),
                                contentDescription = "forward arrow" ,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(5.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }


                if (viewModel.isDialogueShown){
                    if (userData != null) {
                        ProfileDialog(onDismiss = {
                            viewModel.onDismissDialogue()
                        },
                            onConfirm = {
                                viewModel.onOKayClick()
                            },
                            userData = UserData(
                                username = userData?.username,
                                userId = userData.userId,
                                profilePictureUrl = userData.profilePictureUrl
                            ),
                            onSignOut
                        )
                    }
                }
        }
    }
}