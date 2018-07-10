package com.example.tony_albanese.moodtracker.model

import java.io.Serializable

// TODO: Look into optionals for properties
//This class defines the Mood objects that the users can select from on the main screen.
public open class Mood(description: String = "No description specified", imageID: Int, backgroundColor: Int) : Serializable {
    var mDescription: String
    var mImageId: Int
    var mBackgoundColor: Int

    //TODO: Generate an error message if the object is instantiated incorrectly.

    init {
        mDescription = description
        mImageId = imageID
        mBackgoundColor = backgroundColor
    }

}

