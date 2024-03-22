package com.example.societymanagementapp.complaintScreen

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.societymanagementapp.DialogViewModel
import com.example.societymanagementapp.R

@ExperimentalMaterial3Api
@Composable
fun ComplaintBoxScreen(
    viewModel: DialogViewModel
) {
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
                .fillMaxWidth()
                .height(50.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ), onClick = {
                    viewModel.onOKayClick()
                }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,modifier = Modifier.fillMaxSize()) {
                    Text(text = "Add Complaints here",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(10.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                        Image(painterResource(id = R.drawable.mail),contentDescription = "Complaints" )
                    }
                if (viewModel.isDialogueShown){
                    if (viewModel.isDialogueShown){
                        ComplaintDialog(onDismiss = {
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
    }
}