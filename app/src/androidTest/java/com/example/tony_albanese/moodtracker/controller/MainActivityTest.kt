package com.example.tony_albanese.moodtracker.controller

import android.content.Context
import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.tony_albanese.moodtracker.model.DailyMood
import com.google.gson.Gson
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    lateinit var preferences: SharedPreferences

    val PREF_KEY = "KEY"
    var testDailyMood: DailyMood = DailyMood("Description", 0, 0, "Comment", "Date")
    val gson = Gson()
    var testArrayList = ArrayList<DailyMood>()
    val todaysDate = "Today"

    var mainActivity = MainActivity()


    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getContext()
        preferences = context.getSharedPreferences("TEST_PREFERENCES", Context.MODE_PRIVATE)
        testArrayList.add(testDailyMood)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun saveCommentSharedPreferencesTest() {
        val string1 = "Test 1"
        saveCommentToSharedPrefeences(preferences, PREF_KEY, string1)
        val string2 = preferences.getString(PREF_KEY, "")
        assertEquals(string1, string2)
    }

    @Test
    fun getStringFromSharedPreferencesTest() {
        val string1 = "Test"
        preferences.edit().putString(PREF_KEY, string1).apply()
        val string2 = getStringFromSharedPreferences(preferences, PREF_KEY)
        assertEquals(string1, string2)
    }

    @Test
    fun saveDailyMoodTest() {
        saveDailyMoodToSharedPreferences(preferences, PREF_KEY, testDailyMood)
        val string1 = gson.toJson(testDailyMood)
        val string2 = preferences.getString(PREF_KEY, "")
        assertEquals(string1, string2)
    }

    @Test
    fun saveArrayListToSharedPreferencesTest() {
        saveArrayListToSharedPreferences(preferences, PREF_KEY, testArrayList)
        val savedString = preferences.getString(PREF_KEY, "")
        val testString = gson.toJson(testArrayList)
        assertEquals(savedString, testString)
    }

    @Test
    fun testDefaultPreferncesResponse() {
        val string1 = "nothing"
        val string2 = preferences.getString("BAD_KEY", "nothing")
        assertEquals(string1, string2)
    }

    @Test
    fun testConvertFromJSON_dailyMood() {
        val savedString = gson.toJson(testDailyMood)
        val restoredMood: DailyMood = gson.fromJson(savedString, DailyMood::class.java)
        assertEquals(restoredMood.mDescription, testDailyMood.mDescription)
        //TODO: How to check for equality of objects.
    }

    @Test
    fun differentDatesUpdateTest() {

    }

    @Test
    fun sameDatesUpdateTest() {

    }

}