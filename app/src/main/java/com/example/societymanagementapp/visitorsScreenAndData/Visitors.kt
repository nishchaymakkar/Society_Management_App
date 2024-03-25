package com.example.societymanagementapp.visitorsScreenAndData

data class Visitors(
    var residentId:String?="",
    var visitorName:String?="",
    var date:String?="",
    var phoneNumber: String?="",
    var time:String?="",
    var actualArrivalTime:String?="",
    var status:Boolean?= null
)
