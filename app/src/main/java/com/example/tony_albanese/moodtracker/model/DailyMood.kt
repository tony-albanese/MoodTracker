package com.example.tony_albanese.moodtracker.model


class DailyMood(description: String = "No description specified", imageID: Int, backgroundColor: Int, comment: String = "", date: String) : Mood(description,imageID,backgroundColor) {

    val mComment: String
    val mDate: String

    init {
        mComment = comment
        mDate = date
    }
}