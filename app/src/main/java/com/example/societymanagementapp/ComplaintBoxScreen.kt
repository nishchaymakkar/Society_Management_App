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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ComplaintBoxScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .padding(25.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Complaints",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Card(modifier = Modifier
                .padding(horizontal = 25.dp)
                .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ), border = BorderStroke(width = .5.dp, color = Color.Black)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text(text = "Add Complaints here",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(10.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                        Image(painterResource(id = R.drawable.mail),contentDescription = "Complaints" )
                    }
            }
        }
    }
}