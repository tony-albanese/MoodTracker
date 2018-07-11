package com.example.tony_albanese.moodtracker.model

import java.io.Serializable

//This class defines the Mood objects that the users can select from on the main screen.
public open class Mood(description: String = "No description specified", imageID: Int, backgroundColor: Int) : Serializable {
    var mDescription: String
    var mImageId: Int
    var mBackgoundColor: Int

    //TODO: Look into throwing an error if the object is instatiated improperly.
    init {
        mDescription = description
        mImageId = imageID
        mBackgoundColor = backgroundColor
    }

}

