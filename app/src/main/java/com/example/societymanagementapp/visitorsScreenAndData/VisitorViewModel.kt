package com.example.societymanagementapp.visitorsScreenAndData


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ValueElement
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tflite.support.Empty
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



// this VisitorViewModel is for the dialog box to add visitor entry
class VisitorViewModel: ViewModel() {
    var isDialogueShown by mutableStateOf(false)
        private set

    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)


    init {
        fetchVisitors()
    }


    private fun fetchVisitors() {
        val tempList = mutableListOf<Visitors>()
        response.value = DataState.Loading
        FirebaseDatabase.getInstance().getReference("visitors")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(DataSnap in snapshot.children){
                        val visitorName = DataSnap.getValue(Visitors::class.java)
                        if(visitorName!=null)
                            tempList.add(visitorName)
                    }
                    response.value=DataState.Success(tempList)
                }

                override fun onCancelled(error: DatabaseError) {
                    response.value = DataState.Failure(error.message)
                }
            })
            }

    // Your dialogue related functions:
    fun onOKayClick() {
        isDialogueShown = true
    }

    fun onDismissDialogue() {
        isDialogueShown = false
    }
}
