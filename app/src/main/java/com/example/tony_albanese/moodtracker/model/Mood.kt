package com.example.tony_albanese.moodtracker.model

import java.io.Serializable

//This class defines the Mood objects that the users can select from on the main screen.
open class Mood(description: String = "No description specified", imageID: Int, backgroundColor: Int) : Serializable {
    var mDescription: String = description //A description of the mood.
    var mImageId: Int //An image to communicate the meaning of the mood.
    var mBackgoundColor: Int //An integer corresponsing to an Android color resource.

    init {
        mImageId = imageID
        mBackgoundColor = backgroundColor
    }

}

