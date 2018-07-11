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

    val dateFormat = SimpleDateFormat("dd/MM/yyy")
    val dateString = dateFormat.format(date)
    return dateString
}

//TODO: Build unit test for saveDailyMoodtoSharedPreferences.
fun saveDailyMoodToSharedPreferences(sharedPreferences: SharedPreferences, key: String, dailyMood: DailyMood) {
    val gson: Gson = Gson()
    val stringedDailyMood: String = gson.toJson(dailyMood)
    sharedPreferences.edit().putString(key, stringedDailyMood).apply()
}

//TODO: Build unit test for saveCommentToSharedPreferences
fun saveCommentToSharedPrefeences(preferences: SharedPreferences, key: String, string: String) {
    preferences.edit().putString(key, string).apply()
}

//TODO: Build unit test for getStringFromSharedPreferences()
fun getStringFromSharedPreferences(preferences: SharedPreferences, key: String): String {
    //TODO: Change this to null and make it safe to call from the passing function.
    var sharedString = preferences.getString(key, "nothing")
    return sharedString
}

//TODO: Build unit test for saveArrayListToSharedPreferences()
fun saveArrayListToSharedPreferences(preferences: SharedPreferences, key: String, array: ArrayList<DailyMood>) {
    var gson = Gson()
    var stringedArrayList = gson.toJson(array)
    preferences.edit().putString(key, stringedArrayList).apply()
}

fun createToast(context: Context, message: String) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    toast.show()
}