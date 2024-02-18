@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package com.example.societymanagementapp

import android.app.Dialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun VisitorsDialogue(
    onDismiss: ()-> Unit,
    onConfirm:()-> Unit,
) {
    Dialog(onDismissRequest = {
        onDismiss()
    },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )

    ) {
        Card(shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(.95f)
                .border(1.dp, color = Color.Blue, shape = RoundedCornerShape(15.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Row(
                    Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .border(width = .5.dp, color = Color.Black, shape = RectangleShape),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Visitor Entry", fontSize = 25.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp))
                }
                Row(
                    Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .border(width = .5.dp, color = Color.Black, shape = RectangleShape),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Text(text = "Visitor Name",
                        Modifier
                            .padding(horizontal = 10.dp)
                            .weight(4f))
                    var visitorname by remember {
                        mutableStateOf("")
                    }
                    OutlinedTextField(value = visitorname, onValueChange = {visitorname = it},
                        Modifier.weight(6f),
                        maxLines = 1,
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Left,
                            fontSize = 20.sp,
                            color = Color.Black,
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedTextColor = Color.Black ,
                            containerColor = Color.White,
                            cursorColor = Color.Blue,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )
                }
                Row(
                    Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .border(width = .5.dp, color = Color.Black, shape = RectangleShape),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Text(text = "Mobile No.",
                        Modifier
                            .padding(10.dp)
                            .weight(4f))
                    var mobile_no by remember {
                        mutableStateOf("")
                    }
                    OutlinedTextField(value = mobile_no, onValueChange = {mobile_no = it},
                        Modifier.weight(6f),
                        maxLines = 1,
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Left,
                            fontSize = 20.sp,
                            color = Color.Black,
                        ),
                        trailingIcon ={
                            Image(painterResource(id = R.drawable.phoneicon), contentDescription = "phone icon")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedTextColor = Color.Black ,
                            containerColor = Color.White,
                            cursorColor = Color.Blue,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )
                }
                Row(
                    Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .border(width = .5.dp, color = Color.Black, shape = RectangleShape),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Button(onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Blue
                        ), shape = RectangleShape,
                        modifier = Modifier.weight(5f),
                        border = BorderStroke(1.dp, Color.Blue)
                    ) {
                        Text(text = "cancel")
                    }
                    Button(onClick = {  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                            contentColor = Color.White
                        ), shape = RectangleShape,
                        modifier = Modifier.weight(5f)
                    ) {
                        Text(text = "ok")
                    }
                }

            }
        }

    }
}

