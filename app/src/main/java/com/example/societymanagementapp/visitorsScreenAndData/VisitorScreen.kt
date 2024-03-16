@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
package com.example.societymanagementapp.visitorsScreenAndData


import VisitorsDialog
import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.societymanagementapp.R
import com.example.societymanagementapp.ui.theme.Mustard
import com.example.societymanagementapp.ui.theme.Orange
import com.example.societymanagementapp.ui.theme.Pink
import com.example.societymanagementapp.ui.theme.darkGreen
import com.example.societymanagementapp.ui.theme.darkMagenta
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore



@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitorScreen() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        TopAppBar(title = { Text(text = "Visitors Log", fontSize = 20.sp, fontWeight = FontWeight.Bold) }
            , colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White, titleContentColor = Color.Black) )
        var visitorList = mutableStateListOf<Visitors?>()
        val context = LocalContext.current
        var db = FirebaseFirestore.getInstance()
        var visitors = Visitors()
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid
        db.collection("Log").document("Visitors").collection("$userId").get()
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
                }else {
                    // if the snapshot is empty we are displaying
                    // a toast message.
                    Toast.makeText(
                        context,
                        "No data found in Database",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            // if we don't get any data or any error
            // we are displaying a toast message
            // that we donot get any data
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "Fail to get the data.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        ExpandableCard(visitorList)
    }

}


@ExperimentalMaterial3Api
@Composable
fun ExpandableCard( visitorList: SnapshotStateList<Visitors?>,visitorViewModel: VisitorViewModel = viewModel()){
Box(modifier = Modifier
    .fillMaxWidth()
    .fillMaxHeight()) {
    LazyColumn(
        Modifier
            .padding(top = 60.dp)
            .align(Alignment.TopCenter)) {
        itemsIndexed(visitorList) { index, item ->
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(15.dp),
            ) {
                Box(modifier = Modifier.fillMaxSize() ){
                    visitorList[index]?.visitorName?.let{
                    Text(text = it, fontSize = 15.sp,
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.TopStart),
                        fontWeight = FontWeight.Bold , color = Color.Black)}
                    visitorList[index]?.phoneNumber?.let {
                    Text(text = it, fontSize = 12.sp,
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterStart),
                        color = Mustard)}
                    Text(text= "Status", fontSize = 12.sp,
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterEnd),color = Color.Blue)
                    Row(modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(5.dp)) {
                        visitorList[index]?.time?.let {
                        Text(text = it, fontSize = 12.sp,color= darkGreen)}
                        Text(text = " | ",fontSize = 12.sp,color = Color.DarkGray)
                        Text(text = "Arrival Time", fontSize = 12.sp,color = Color.Red )
                    }
                    visitorList[index]?.date?.let{
                    Text(text = it, fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(5.dp),color= Orange)}
                }


            }
        }
    }
    Row(Modifier.align(Alignment.BottomEnd).padding(15.dp)) {
        FloatingActionButton(onClick = { visitorViewModel.onOKayClick() },
            containerColor =  Color.White, shape = CircleShape ) {
            Image(
                painterResource(id = R.drawable.add_circle),
                contentDescription = "add visitors",
                modifier = Modifier.size(60.dp)
            )
            if (visitorViewModel.isDialogueShown) {
                VisitorsDialog(onDismiss = {
                    visitorViewModel.onDismissDialogue()
                },
                    onConfirm = {
                        visitorViewModel.onOKayClick()
                    }
                )
            }
        }


    }
    }
}


    

