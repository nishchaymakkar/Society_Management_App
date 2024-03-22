package com.example.societymanagementapp.visitorsScreenAndData

data class Visitors(
    val visitorName:String?="",
    val date:String?="",
    val phoneNumber: String?="",
    val time:String?="",
    val actualArrivalTime:String?="",
    val status:Boolean?=false
)
