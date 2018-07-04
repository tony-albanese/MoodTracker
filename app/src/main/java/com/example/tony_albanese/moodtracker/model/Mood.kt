package com.example.tony_albanese.moodtracker.model
// TODO: Look into optionals for properties
//This class defines the Mood objects that the users can select from on the main screen.
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

