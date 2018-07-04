package com.example.tony_albanese.moodtracker.controller

import java.text.SimpleDateFormat
import java.util.*

fun convertDate(date: Date): String {

    val dateFormat = SimpleDateFormat("dd/MM/yyy")
    val dateString = dateFormat.format(date)
    return dateString
}