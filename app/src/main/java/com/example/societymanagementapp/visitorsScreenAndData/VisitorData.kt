package com.example.societymanagementapp.visitorsScreenAndData

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object VisitorData {
    class visitorData(
        var visitorName: String,
        var expectedArrival: String,
        var actualArrival: String,
        var phoneNumber: String,
        var Status: String,
        var Date: String,
    )

    private val _datalist = MutableStateFlow<List<visitorData>>(emptyList())
    val datalist: StateFlow<List<visitorData>> = _datalist.asStateFlow()

    // Function to access the data list
    fun getVisitorDataList(): StateFlow<List<visitorData>> {
        return datalist
    }

    // Function to update the data list
    fun updateVisitorDataList(visitors: List<visitorData>) {
        _datalist.value = visitors
    }
}