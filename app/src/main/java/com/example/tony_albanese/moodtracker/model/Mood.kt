package com.example.tony_albanese.moodtracker.model

public class Mood(description: String = "No description specified", imageID: Int = 0)   {
    val mDescription: String
    val mImageId: Int


    init {
        mDescription = description
        mImageId = imageID
    }

}