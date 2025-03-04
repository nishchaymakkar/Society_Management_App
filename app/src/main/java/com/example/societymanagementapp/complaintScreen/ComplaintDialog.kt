package com.example.societymanagementapp.complaintScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.societymanagementapp.R

@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ComplaintDialogPreview() {
    ComplaintDialog(onDismiss = {},onConfirm = {})
}

@ExperimentalMaterial3Api
@Composable
fun ComplaintDialog(
    onDismiss: ()-> Unit,
    onConfirm:()-> Unit ,
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )

    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(.95f)
                .border(1.dp, color = Color.Transparent, shape = RoundedCornerShape(15.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)

            ) {
                var complainttopic by remember {
                    mutableStateOf("")
                }
                OutlinedTextField(value = complainttopic, onValueChange = {complainttopic = it},
                    maxLines = 2,
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        color = Color.Black
                    ), colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.Black,
                        containerColor = Color.White,
                        focusedLabelColor = Color.Blue,
                        unfocusedLabelColor = Color.Black,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Blue,
                    ), label = {
                        Text(text = "Complaint Topic")
                    }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth()
                    )
                var complaint by remember {
                    mutableStateOf("")
                }

                OutlinedTextField(value = complaint
                    , onValueChange = {complaint = it},
                    maxLines = 5,
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        color = Color.Black,
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.Black ,
                        containerColor = Color.White,
                        cursorColor = Color.Blue,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Blue,
                        focusedLabelColor = Color.Blue
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    placeholder = {
                                  Text(text = "Write you complaint here")
                    },

                    modifier= Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(vertical = 5.dp)
                )
                Row(
                    Modifier
                        .align(alignment = Alignment.End),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Blue
                        ),
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 5.dp),
                        border = BorderStroke(1.dp, Color.Blue)
                    ) {
                        Text(text = "cancel")
                    }
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 5.dp),
                        border = BorderStroke(1.dp, Color.Blue)
                    ) {
                        Text(text = "send",Modifier.padding(horizontal= 3.dp))
                        Image(painterResource(id = R.drawable.send), contentDescription = "send icon",Modifier.size(15.dp),
                            colorFilter = ColorFilter.tint(color = Color.White)  )
                    }
                }
            }
        }
    }
}