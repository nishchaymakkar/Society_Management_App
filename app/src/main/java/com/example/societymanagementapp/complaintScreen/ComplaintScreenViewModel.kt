package com.example.societymanagementapp.complaintScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ComplaintScreenViewModel : ViewModel(){
    var isDialogueShown by mutableStateOf(false)
        private set

    fun onOKayClick(){
        isDialogueShown = true
    }
    fun onDismissDialogue(){
        isDialogueShown = false
    }
}