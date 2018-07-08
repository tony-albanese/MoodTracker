package com.example.tony_albanese.moodtracker.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import com.example.tony_albanese.moodtracker.R
import com.example.tony_albanese.moodtracker.model.DailyMood
import com.example.tony_albanese.moodtracker.model.Mood
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var preferences: SharedPreferences
    private var layoutManager: RecyclerView.LayoutManager? = null //Create a reference to the layout manager that will organize the views in the RecyclerAdapter.
    private var adapter: RecyclerView.Adapter<MoodRecyclerAdapter.ViewHolder>? = null //Create a reference to our custom adapter.
    private var moodList = ArrayList<Mood>(); //This is the object that will contain a collection of Mood objects for display.

    lateinit var currentDailyMood: DailyMood
    lateinit var todaysDate: Date

    var dailyComment: String = ""
    var dailyMoodList = ArrayList<DailyMood>()
    val MAX_HISTORY_SIZE = 7;

    val KEY_DAILY_MOOD: String = "KEY_DAILY_MOOD"
    val KEY_DAILY_MOOD_LIST: String = "KEY_DAILY_MOOD_LIST"
    val KEY_COMMENT: String = "KEY_COMMENT"
    lateinit var dailyMoodData: String
    lateinit var dailyMoodListData: String

    //onCreate() function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeObjects()
        loadSharedPreferences()
        foo()

        //Set the click listener for the fab to navigate to the MoodHistoryActivity.
        root_frame_layout.fab_mood_history.setOnClickListener { v: View ->
            val intent: Intent = Intent(this, MoodHistoryActivity::class.java)
            intent.putExtra(KEY_DAILY_MOOD_LIST, dailyMoodList as Serializable)
            startActivity(intent)
        }

        //Set the click listener for the comment fab that will trigger the generation of the dialog.
        root_frame_layout.fab_add_comment.setOnClickListener { v: View ->
            createCommentDialogue()
        }
    }

    //This function creates the dialog.
    fun createCommentDialogue() {
        var commentText: EditText = EditText(this)
        //TODO: Format the EditText object
        var dialog = AlertDialog.Builder(this)
        dialog.setView(commentText)
        dialog.setTitle("How are you feeling?")
                .setMessage("Enter a comment.")
                .setNegativeButton("Cancel", null)
        dialog.setPositiveButton("OK"){
            dialog, button ->
            dailyComment = commentText.text.toString()
            currentDailyMood.mComment = dailyComment
            saveCommentToSharedPrefeences(preferences, KEY_COMMENT, dailyComment)
            saveDailyMoodToSharedPreferences(preferences, KEY_DAILY_MOOD, currentDailyMood)
            foo()
        }
        dialog.create()
        dialog.show()
        //TODO: Clean the user input before setting it.
    }

    //This is the function we want called when the user clicks on a mood in the list.
    private fun moodItemClicked(mood: Mood) {
        var message = "The current mood has been set to: ${mood.mDescription}"

        currentDailyMood.mDescription = mood.mDescription
        currentDailyMood.mImageId = mood.mImageId
        currentDailyMood.mBackgoundColor = mood.mBackgoundColor

        saveDailyMoodToSharedPreferences(preferences, KEY_DAILY_MOOD, currentDailyMood)
        createToast(applicationContext, message)
        foo()
    }

    //This is the function that generates the moods the user can select.
    fun generateMoodSelectionList() {
        moodList.add(Mood(getString(R.string.mood_happy), R.mipmap.smiley_happy, R.color.color_happy))
        moodList.add(Mood(getString(R.string.mood_super_happy), R.mipmap.smiley_super_happy, R.color.color_super_happy))
        moodList.add(Mood(getString(R.string.mood_normal), R.mipmap.smiley_normal, R.color.color_normal))
        moodList.add(Mood(getString(R.string.mood_disappointed), R.mipmap.smiley_disappointed, R.color.color_disappointed))
        moodList.add(Mood(getString(R.string.mood_sad), R.mipmap.smiley_sad, R.color.color_sad))
    }

    //This function will initialize the state of the objects.
    fun initializeObjects(){
        todaysDate = Date()
        preferences = getPreferences(Context.MODE_PRIVATE)

        generateMoodSelectionList() //Function that populates the ArrayList with Mood objects.

        layoutManager = LinearLayoutManager(this) //Our layoutManager holds an instance of a LinearLayoutManager
        recycler_view.layoutManager = layoutManager //Attach the layout manager to the recycler_view.
        adapter = MoodRecyclerAdapter(moodList, { mood: Mood -> moodItemClicked(mood) }) //Initialize our adapter variable with a MoodReyclerAdapter object. We pass in our data as a paramater.
        recycler_view.adapter = adapter //Set the adapter property of the recycler_view to the adapter we just created.
    }

    //This function generates the defaultMood.
    fun generateDefaultDailyMood(){
        val date = Date()
        dailyComment = String()
        currentDailyMood = DailyMood(getString(R.string.mood_happy), R.mipmap.smiley_happy, R.color.color_happy, dailyComment, convertDate(date))
        saveDailyMoodToSharedPreferences(preferences, KEY_DAILY_MOOD, currentDailyMood)
        saveCommentToSharedPrefeences(preferences,KEY_COMMENT, dailyComment)
    }

    //This function loads the values from SharedPreferences
    fun loadSharedPreferences(){
        dailyMoodData = getStringFromSharedPreferences(preferences, KEY_DAILY_MOOD)
            if(dailyMoodData == "nothing"){
                generateDefaultDailyMood()
            } else {
                var gson = Gson()
                currentDailyMood = gson.fromJson(dailyMoodData, DailyMood::class.java)
            }
        dailyMoodListData = getStringFromSharedPreferences(preferences, KEY_DAILY_MOOD_LIST)
            if(dailyMoodListData != "nothing"){
                var gson = Gson()
                val type = object : TypeToken<ArrayList<DailyMood>>() {
                }.type
                dailyMoodList = gson.fromJson(dailyMoodListData, type)
            }
        dailyComment = getStringFromSharedPreferences(preferences, KEY_COMMENT)
        foo()
    }

    //Thus function checks current date against the object's date and adds to the list if they don't match.
    fun foo (){
        //If the dates don't align, the day has changed. Add the the currentDailyMood to the list. Then,
        //reset the dailyMood object.
        if(currentDailyMood.mDate != convertDate(todaysDate))
        {
            dailyMoodList.add(DailyMood(currentDailyMood.mDescription, currentDailyMood.mImageId, currentDailyMood.mBackgoundColor, dailyComment, currentDailyMood.mDate))
            checkArraySize()
            saveArrayListToSharedPreferences(preferences, KEY_DAILY_MOOD_LIST, dailyMoodList)
            generateDefaultDailyMood()
        }
    }

    fun checkArraySize(){
        var index: Int = dailyMoodList.size
        while(index > MAX_HISTORY_SIZE){
            dailyMoodList.removeAt(0)
            index = dailyMoodList.size
        }
    }
}