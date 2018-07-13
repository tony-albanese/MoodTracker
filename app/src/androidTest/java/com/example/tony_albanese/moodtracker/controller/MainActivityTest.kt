package com.example.tony_albanese.moodtracker.controller

import android.content.Context
import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    lateinit var preferences: SharedPreferences

    val PREF_KEY = "KEY"

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getContext()
        preferences = context.getSharedPreferences("TEST_PREFERENCES", Context.MODE_PRIVATE)
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

}