import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.societymanagementapp.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@ExperimentalMaterial3Api
@Composable
fun VisitorDialogPreview(){
    VisitorsDialog(
        onDismiss = {},
        onConfirm = {},
    )
}
@ExperimentalMaterial3Api
@Composable
fun VisitorsDialog(
    onDismiss: ()-> Unit,
    onConfirm:()-> Unit ,
) {
    val context = LocalContext.current
    var date by remember { mutableStateOf("")}
    var time by remember { mutableStateOf("") }
    val db = Firebase.firestore
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
                .border(1.dp, color = Color.Transparent, shape = RoundedCornerShape(15.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color.White),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Row(
                    Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Visitor Entry", fontSize = 25.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp),color = Color.Blue)
                }
                // textfield for Visitor Name
                var visitorname by remember {
                    mutableStateOf("")
                }
                OutlinedTextField(value = visitorname, onValueChange = {visitorname = it},
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
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Blue,
                        focusedLabelColor = Color.Blue
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = {
                        Text(
                            text = "Visitor Name"
                        )
                    },
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                )

                //textfield for visitor phone no.
                var phone_no by remember {
                    mutableStateOf("")
                }
                OutlinedTextField(value = phone_no, onValueChange = {phone_no = it},
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
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Blue,
                        focusedLabelColor = Color.Blue
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    label = {
                        Text(
                            text = "Phone No."
                        )
                    },
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                )

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    //the value of date  picked is stored in pickedDate
                    var pickedDate by remember {
                        mutableStateOf(LocalDate.now())
                    }
                    //the value of time picked is stored in pickedtime
                    var pickedTime by remember {
                        mutableStateOf(LocalTime.NOON)
                    }
                    val formattedDate by remember {
                        derivedStateOf{
                            DateTimeFormatter
                                .ofPattern("mmm dd yyyy")
                                .format(pickedDate)
                        }
                    }
                    val formattedTime by remember {
                        derivedStateOf{
                            DateTimeFormatter
                                .ofPattern("hh:mm")
                                .format(pickedTime)
                        }
                    }
                    val dateDialogState = rememberMaterialDialogState()
                    val timeDialogState = rememberMaterialDialogState()
                    //this materialdialog is for the date picker
                    MaterialDialog(
                        dialogState = dateDialogState,
                        properties = DialogProperties(

                        ),
                        buttons = {
                            positiveButton(text = "Ok")
                            negativeButton(text = "Cancel")
                        }
                    ) {
                        datepicker(
                            initialDate = LocalDate.now(),
                            title = "Pick a Date"
                        ){
                            pickedDate = it
                            date = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(it)
                        }
                    }
                    // the text value of date is stored in date

                    //textfield for the date
                    OutlinedTextField(value = date, onValueChange = { date= it},
                        maxLines = 1,
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Left,
                            fontSize = 12.sp,
                            color = Color.Black,
                        ),
                        trailingIcon ={
                            IconButton(onClick = {
                                dateDialogState.show()
                            }) {
                                Image(painterResource(id = R.drawable.calander), contentDescription = "phone icon")
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedTextColor = Color.Black ,
                            containerColor = Color.White,
                            cursorColor = Color.Blue,
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Blue,
                            focusedLabelColor = Color.Blue
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text(
                                text = "Date", fontSize = 12.sp
                            )
                        },
                        modifier= Modifier
                            .padding(vertical = 5.dp)
                            .weight(4f)
                    )
                    Spacer(modifier = Modifier
                        .width(5.dp)
                        .weight(.5f))
                    // this materialDialog is for the TimePicker
                    MaterialDialog(
                        dialogState = timeDialogState,
                        properties = DialogProperties(

                        ),
                        buttons = {
                            positiveButton(text = "Ok")
                            negativeButton(text = "Cancel")
                        }
                    ) {
                        timepicker(
                            initialTime = LocalTime.now(),
                            title = "Pick a Time"
                        ){
                            pickedTime = it
                            time = DateTimeFormatter.ofPattern("hh:mm a").format(it)
                        }
                    }
                    //the value of text time is stored in time

                    //textfield for the time
                    OutlinedTextField(value = time, onValueChange = {time = it},
                        maxLines = 1,
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Left,
                            fontSize = 12.sp,
                            color = Color.Black,
                        ),
                        trailingIcon = {
                            IconButton(onClick = {
                                timeDialogState.show()
                            }) {
                                Image(painterResource(id = R.drawable.clock), contentDescription = "phone icon")
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedTextColor = Color.Black ,
                            containerColor = Color.White,
                            cursorColor = Color.Blue,
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Blue,
                            focusedLabelColor = Color.Blue
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text(
                                text = "Arrival Time",fontSize = 12.sp
                            )
                        },
                        modifier= Modifier
                            .padding(vertical = 5.dp)
                            .weight(5f)
                    )

                }



// Buttons for dismiss and confirm
                Row(
                    Modifier
                        .align(alignment = Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Button(onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Blue
                        ),
                        modifier = Modifier
                            .weight(5f)
                            .padding(horizontal = 5.dp, vertical = 10.dp),
                        border = BorderStroke(1.dp, Color.Blue)
                    ) {
                        Text(text = "cancel")
                    }
                    Button(onClick = {val visitorData = hashMapOf(
                        "visitorName" to visitorname,
                        "phoneNumber" to phone_no,
                        "date" to date,
                        "time" to time
                    )
                        db.collection("visitors")
                            .add(visitorData)
                            .addOnSuccessListener { documentReference ->

                                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

                                Toast.makeText(context, "Visitor added successfully!", Toast.LENGTH_SHORT).show()

                                onDismiss()
                            }
                            .addOnFailureListener { e ->

                                Log.w(TAG, "Error adding document", e)

                                Toast.makeText(context, "Error adding visitor: ${e.message}", Toast.LENGTH_SHORT).show()

                            }
                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(5f)
                            .padding(horizontal = 5.dp, vertical = 10.dp),
                        border = BorderStroke(1.dp,Color.Blue)
                    ) {
                        Text(text = "ok")
                    }
                }

            }
        }

    }
}
