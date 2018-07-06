package com.example.tony_albanese.moodtracker.model


class DailyMood(description: String = "No description specified", imageID: Int, backgroundColor: Int, comment: String = "", date: String) : Mood(description,imageID,backgroundColor) {

    var mComment: String
    var mDate: String

    init {
        mComment = comment
        mDate = date
    }
}