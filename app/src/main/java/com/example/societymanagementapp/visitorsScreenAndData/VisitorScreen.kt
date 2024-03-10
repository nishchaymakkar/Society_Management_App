@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
package com.example.societymanagementapp.visitorsScreenAndData





import VisitorsDialog
import android.content.Context
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
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
import com.example.societymanagementapp.ui.theme.Orange
import com.example.societymanagementapp.ui.theme.Pink
import com.example.societymanagementapp.ui.theme.darkGreen
import com.example.societymanagementapp.ui.theme.darkMagenta


@ExperimentalMaterial3Api
@Preview
@Composable
fun VisitorScreenPreview() {

   VisitorScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitorScreen() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        TopAppBar(title = { Text(text = "Visitors Log", fontSize = 20.sp, fontWeight = FontWeight.Bold) }
            , colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White, titleContentColor = Color.Black) )
        Column {
            ExpandableCard( visitorList = SnapshotStateList())
        }


    }

}

//context = LocalContext.current, visitorList = SnapshotStateList()
@Composable
fun ExpandableCard( visitorList: SnapshotStateList<Visitors?>,visitorViewModel: VisitorViewModel = viewModel()){
Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
    LazyColumn(Modifier.padding(top = 60.dp).align(Alignment.TopCenter)) {
        itemsIndexed(visitorList) { index, item ->
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(15.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    visitorList[index]?.visitorName?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.TopStart),
                            color = Color.Blue,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    visitorList[index]?.date?.let {
                        Text(
                            text = it,

                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.TopEnd),
                            style = TextStyle(
                                color = darkGreen,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                    }

                    visitorList[index]?.phoneNumber?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.CenterStart),
                            color = Pink,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    visitorList[index]?.time?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.CenterEnd),
                            color = Color.Red, fontSize = 15.sp, fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "Status",
                        modifier = Modifier
                            .align(alignment = Alignment.BottomStart)
                            .padding(10.dp),
                        color = darkMagenta,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Arrival time",
                        modifier = Modifier
                            .align(alignment = Alignment.BottomEnd)
                            .padding(10.dp),
                        color = Orange,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
            }
        }
    }
    Row(Modifier.align(Alignment.BottomEnd)) {
        Card(elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
            modifier = Modifier.clip(shape = CircleShape).padding(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            onClick = {
                visitorViewModel.onOKayClick() // Call the onOKayClick function from the view model
            }
        ) {
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


