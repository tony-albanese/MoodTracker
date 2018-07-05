package com.example.tony_albanese.moodtracker.controller

import android.content.Context
import android.widget.Toast
import com.example.tony_albanese.moodtracker.model.DailyMood
import com.example.tony_albanese.moodtracker.model.Mood
import java.text.SimpleDateFormat
import java.util.*

//This simple function converts a date object into a readable String for the app.
fun convertDate(date: Date): String {

    val dateFormat = SimpleDateFormat("dd/MM/yyy")
    val dateString = dateFormat.format(date)
    return dateString
}

fun saveParametersToSharedPreferences(){

}

fun updateDailyMoodArrayList(list: ArrayList<DailyMood>, newMood: Mood){

}

fun createToast(context: Context, message:String){
   val toast =  Toast.makeText(context, message, Toast.LENGTH_SHORT)
    toast.show()
    //TODO: Add some control to prevent the user from clicking like crazy.
}