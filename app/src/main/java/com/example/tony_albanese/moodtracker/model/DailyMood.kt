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

    fun equals(other: DailyMood): Boolean {

        if(other == null) {
            return false
        }
        if(this.mDescription != other.mDescription) {
            return false
        }

        if(this.mComment != other.mComment) {
            return false
        }

        if(this.mDate != other.mDate) {
            return false
        }

        if(this.mBackgoundColor != other.mBackgoundColor){
            return false
        }

        if(this.mImageId != other.mImageId){
            return false
        }
        else return true
    }



}