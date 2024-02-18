package com.example.societymanagementapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class VisitorViewModel: ViewModel() {
    var isDialogueShown by mutableStateOf(false)
        private set

    fun onOKayClick(){
        isDialogueShown = true
    }
    fun onDismissDialogue(){
        isDialogueShown = false
    }
}

