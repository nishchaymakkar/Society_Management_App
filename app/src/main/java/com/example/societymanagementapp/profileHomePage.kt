package com.example.societymanagementapp

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
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.societymanagementapp.ui.theme.offWhite


@Preview
@Composable
fun profileHomePage() {

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(offWhite)
    ){
        Column {
            //first row for profilephoto and application logo
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier =Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {

                    Image(painterResource(id = R.drawable.logohere), contentDescription = "Society logo",
                        Modifier.size(30.dp))

                Spacer(modifier = Modifier.width(140.dp))

                    Image(painterResource(id =  R.drawable.person), contentDescription ="profilePhoto")

            }
        }
    }
}