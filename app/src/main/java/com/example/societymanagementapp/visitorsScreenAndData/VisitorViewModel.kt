package com.example.societymanagementapp.visitorsScreenAndData


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
// this VisitorViewModel is for the dialog box to add visitor entry
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