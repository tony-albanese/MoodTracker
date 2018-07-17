package com.example.tony_albanese.moodtracker.model

import java.io.Serializable

//Class to represent a DailyMood object. This is the object inherits from Mood.
class DailyMood(description: String = "No description specified", imageID: Int, backgroundColor: Int, comment: String = "", date: String) : Mood(description, imageID, backgroundColor), Serializable, Comparable<DailyMood> {

    var mComment: String = comment //Opional comment to go along with the mood.
    var mDate: String //A string representing the date the mood was recorded.

    init {
        mDate = date
    }

    override fun compareTo(other: DailyMood): Int {
     return 0
    }
}