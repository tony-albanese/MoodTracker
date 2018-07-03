package com.example.tony_albanese.moodtracker.model

public class Mood(description: String = "No description specified", imageID: Int, backgroundColor: Int)   {
    val mDescription: String
    val mImageId: Int
    val mBackgoundColor: Int


    init {
        mDescription = description
        mImageId = imageID
        mBackgoundColor = backgroundColor
    }

}

