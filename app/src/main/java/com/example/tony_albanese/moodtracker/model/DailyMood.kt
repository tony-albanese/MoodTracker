package com.example.tony_albanese.moodtracker.model

import java.io.Serializable


class DailyMood(description: String = "No description specified", imageID: Int, backgroundColor: Int, comment: String = "", date: String) : Mood (description,imageID,backgroundColor), Serializable {

    var mComment: String
    var mDate: String

    init {
        mComment = comment
        mDate = date
    }
}