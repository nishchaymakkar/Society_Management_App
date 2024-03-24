package com.example.societymanagementapp.visitorsScreenAndData

data class Visitors(
    val residentId:String?="",
    val visitorName:String?="",
    val date:String?="",
    val phoneNumber: String?="",
    val time:String?="",
    val actualArrivalTime:String?="",
    val status:Boolean?=false
)
