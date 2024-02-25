package com.example.societymanagementapp.visitorsScreenAndData
//In this file all the data from database is collected in list to show in the visitor log
object VisitorData{
    class visitorData(
        var visitorName: String,
        var expectedArrival: String,
        var actualArrival: String,
        var Phoneno: String,
        var Status: String,
        var Date: String,
    )
    var datalist = mutableListOf(
        visitorData(
            visitorName = "Nishchay",
            expectedArrival = "08:30 P.M",
            actualArrival = "08:00 P.M",
            Phoneno = "7217689599",
            Status = "Arrived",
            Date = "22 Feb 2024"
        )

    )
}