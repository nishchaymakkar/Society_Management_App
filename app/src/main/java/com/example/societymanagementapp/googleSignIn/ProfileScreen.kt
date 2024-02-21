@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.societymanagementapp.googleSignIn

import android.service.autofill.OnClickAction
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.societymanagementapp.R
import com.example.societymanagementapp.ui.theme.darkGreen
import com.example.societymanagementapp.ui.theme.offWhite


@Composable
fun ProfileScreen(
    userData: UserData? ,
    onSignOut: () -> Unit,
    onVisitorClick: () -> Unit,
    onComplaintClick: () -> Unit
) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(offWhite)
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
                        painterResource(id = R.drawable.logohere), contentDescription = "Society logo",
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
                        AsyncImage(model = userData.profilePictureUrl, contentDescription ="profilePicture",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
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
                Text(text = "Appartment Address",fontSize = 15.sp, color = Color.DarkGray)
            }
            Spacer(modifier = Modifier.height(20.dp))

            Column {
                Row {
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(horizontal = 20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        onClick = onVisitorClick
                    ) {
                        Row(horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Expected Guest", color = darkGreen,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Today at 1:20 P.M",
                                fontSize = 15.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(10.dp)
                            )
                            Text(text = ".more details",
                                color = Color.Blue,
                                modifier = Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
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
                        onClick = onVisitorClick

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
                        ), onClick = onComplaintClick
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
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = onSignOut,) {
                Text(text = "Sign Out",
                    color = Color.White)
            }
        }
    }
}