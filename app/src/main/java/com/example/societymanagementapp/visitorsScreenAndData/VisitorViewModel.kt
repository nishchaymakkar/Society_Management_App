package com.example.societymanagementapp.visitorsScreenAndData


import android.util.Log
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
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


// this VisitorViewModel is for the dialog box to add visitor entry
class VisitorViewModel: ViewModel() {
    var isDialogueShown by mutableStateOf(false)
        private set


    // Your dialogue related functions:
    fun onOKayClick() {
        isDialogueShown = true
    }

    fun onDismissDialogue() {
        isDialogueShown = false
    }

    val state1 = mutableStateOf(Visitors())
    init {
        getData()
    }
    private fun getData(){
        viewModelScope.launch {
            state1.value = getDataFromFireStore()
        }
    }

}


suspend fun getDataFromFireStore():Visitors{
    val db = FirebaseFirestore.getInstance()
    var visitors = Visitors()
    try{
        db.collection("visitors").get().await().map(){
            val result =   it.toObject(Visitors::class.java)
            visitors = result
        }
    }catch (e: FirebaseFirestoreException){
        Log.d("error","getDataFromFireStore: $e")
    }
    return visitors

}
