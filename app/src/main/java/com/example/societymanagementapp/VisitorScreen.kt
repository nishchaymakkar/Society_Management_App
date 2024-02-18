@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.societymanagementapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun VisitorScreen(
    viewModel: VisitorViewModel
): Unit {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Column {
        Row(
            modifier = Modifier.padding(25.dp)
        ) {
            Text(text = "Visitors Log",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(0.dp))
            LazyColumn(modifier = Modifier.padding(horizontal = 15.dp)){
                items(count = 1){
                    Card {
                        Text(text = "Visitor", fontSize = 25.sp)
                        Divider(Modifier.height(10.dp), color = Color.White)
                    }
                }
            }
        }
        //add visitor 
        Card(
            modifier = Modifier
                .clip(shape = CircleShape)
                .align(Alignment.BottomEnd)
                .padding(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent),
            onClick = {
                viewModel.onOKayClick()
            }
        ) {
            Image(painterResource(id = R.drawable.add_circle),
                contentDescription = "add visitors",
                modifier = Modifier.size(70.dp)

            )
            if (viewModel.isDialogueShown){
                VisitorsDialogue(onDismiss = {
                    viewModel.onDismissDialogue()
                },
                    onConfirm = {
                        viewModel.onOKayClick()
                    }
                )
            }
        }

    }
}