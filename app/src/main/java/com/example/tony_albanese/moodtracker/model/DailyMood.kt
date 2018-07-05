package com.example.tony_albanese.moodtracker.model


class DailyMood(description: String = "No description specified", imageID: Int, backgroundColor: Int, comment: String = "", date: String) : Mood(description,imageID,backgroundColor) {

    val mComment: String //TODO: Find out how to safely allow this string to be null.
    val mDate: String

    init {
        mComment = comment
        mDate = date
    }
}