@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
package com.example.societymanagementapp.visitorsScreenAndData





import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.societymanagementapp.R
import com.example.societymanagementapp.ui.theme.Orange
import com.example.societymanagementapp.ui.theme.Pink
import com.example.societymanagementapp.ui.theme.darkGreen
import com.example.societymanagementapp.ui.theme.darkMagenta

@ExperimentalMaterial3Api
@Preview
@Composable
fun VisitorScreenPreview() {
    VisitorScreen(viewModel = VisitorViewModel())
}
@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitorScreen(
    viewModel: VisitorViewModel
): Unit {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.padding(25.dp)
            ) {
                Text(text = "Visitors Log",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(0.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth()){
                items(VisitorData.datalist){
                    ExpandableCard(data = it)
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
                VisitorsDialog(onDismiss = {
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(
    data: VisitorData.visitorData //the data from the VisitorData file
) {
    var expandableState by remember { mutableStateOf(false) }

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp,
        ),colors = CardDefaults.cardColors(
            containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal=15.dp, vertical = 2.dp)
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),shape = RoundedCornerShape(15.dp),
        onClick = {
            expandableState = !expandableState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text(text = data.visitorName,
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(4f),color = Color.Blue
                       , fontSize = 15.sp, fontWeight = FontWeight.Bold)

                ClickableText(text = AnnotatedString(data.Status),
                    onClick = {
                        expandableState = !expandableState
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                    style = TextStyle(color = darkGreen, fontSize = 15.sp,fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if(expandableState){
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Box(modifier = Modifier
                        .height(75.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()){
                        Text(text = data.Phoneno,
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .padding(10.dp),color = Pink, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text(text = data.Date,
                            modifier = Modifier
                                .align(alignment = Alignment.TopEnd)
                                .padding(10.dp),color = darkMagenta, fontSize = 15.sp,fontWeight = FontWeight.Bold)
                        Text(text = data.expectedArrival,
                            modifier = Modifier
                                .align(alignment = Alignment.BottomStart)
                                .padding(10.dp),color = Orange, fontSize = 15.sp,fontWeight = FontWeight.Bold)
                        Text(text = data.actualArrival,
                            modifier = Modifier
                                .align(alignment = Alignment.BottomEnd)
                                .padding(10.dp),
                            color = Color.Red, fontSize = 15.sp,fontWeight = FontWeight.Bold)
                    }
                }
        }
        }
    }
}