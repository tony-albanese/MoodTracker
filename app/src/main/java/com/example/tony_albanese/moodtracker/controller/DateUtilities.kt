package com.example.tony_albanese.moodtracker.controller

import java.text.SimpleDateFormat
import java.util.*
//This simple function converts a date object into a readable String for the app.
fun convertDate(date: Date): String {

    val dateFormat = SimpleDateFormat("dd/MM/yyy")
    val dateString = dateFormat.format(date)
    return dateString
}