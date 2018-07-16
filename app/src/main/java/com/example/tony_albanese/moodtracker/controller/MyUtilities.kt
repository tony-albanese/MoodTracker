package com.example.tony_albanese.moodtracker.controller

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.tony_albanese.moodtracker.model.DailyMood
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

//This simple function converts a date object into a readable String for the app.
//TODO: Build unit test for convertDate()
fun convertDate(date: Date): String {

    val dateFormat = SimpleDateFormat("dd/MM/yyy") //Date should be in 02/02/1982 format.
    val dateString = dateFormat.format(date)
    return dateString
}

//Share the daily mood to shared preferences after converting it to JSON string.
fun saveDailyMoodToSharedPreferences(sharedPreferences: SharedPreferences, key: String, dailyMood: DailyMood) {
    val gson: Gson = Gson()
    val stringedDailyMood: String = gson.toJson(dailyMood)
    sharedPreferences.edit().putString(key, stringedDailyMood).apply()
}

//Save the comment to shared preferences.
fun saveCommentToSharedPrefeences(preferences: SharedPreferences, key: String, string: String) {
    preferences.edit().putString(key, string).apply()
}

//Get a string from SharedPreferences.
fun getStringFromSharedPreferences(preferences: SharedPreferences, key: String): String {
    val sharedString = preferences.getString(key, "")
    return sharedString
}

//Save the ArrayList to SharedPreferences after storing it as JSON data.
fun saveArrayListToSharedPreferences(preferences: SharedPreferences, key: String, array: ArrayList<DailyMood>) {
    val gson = Gson()
    val stringedArrayList = gson.toJson(array)
    preferences.edit().putString(key, stringedArrayList).apply()
}

//Function creates a toast with the given message.
fun createToast(context: Context, message: String) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    toast.show()
}

//Function to check the arraylist size and clean it up if it is too big.
fun cleanArrayList(size: Int, array: ArrayList<DailyMood>): ArrayList<DailyMood>{
    var index: Int = array.size
    while (index > size) {
        array.removeAt(0)
        index = array.size
    }
    return array
}