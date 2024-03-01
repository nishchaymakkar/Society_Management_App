package com.example.societymanagementapp.visitorsScreenAndData


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// this VisitorViewModel is for the dialog box to add visitor entry
class VisitorViewModel: ViewModel() {
    var isDialogueShown by mutableStateOf(false)
        private set

    val visitorList: StateFlow<List<VisitorData.visitorData>> = VisitorData.getVisitorDataList()

    init {
        viewModelScope.launch {fetchVisitors()
        }
    }

    private suspend fun fetchVisitors() {
        val db = Firebase.firestore
        db.collection("visitors")
            .get()
            .addOnSuccessListener { result ->
                val visitors = result.documents.mapNotNull {
                    it.toObject(VisitorData.visitorData::class.java)
                }
                VisitorData.updateVisitorDataList(visitors)
            }
            .addOnFailureListener {

            }
    }

    // Your dialogue related functions:
    fun onOKayClick() {
        isDialogueShown = true
    }

    fun onDismissDialogue() {
        isDialogueShown = false
    }
}
