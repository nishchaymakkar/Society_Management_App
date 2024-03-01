package com.example.societymanagementapp.visitorsScreenAndData

sealed class DataState {
    class Success(val data : MutableList<Visitors>):DataState()
    class Failure(val message:String):DataState()
    object Loading: DataState()
    object Empty: DataState()
}
