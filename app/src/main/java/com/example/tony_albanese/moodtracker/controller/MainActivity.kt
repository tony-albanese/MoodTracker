package com.example.tony_albanese.moodtracker.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
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
    private lateinit var preferences: SharedPreferences //Need a reference to SharedPreferences to store data.
    private var layoutManager: RecyclerView.LayoutManager? = null //Create a reference to the layout manager that will organize the views in the RecyclerAdapter.
    private var adapter: RecyclerView.Adapter<MoodRecyclerAdapter.ViewHolder>? = null //Create a reference to our custom adapter.
    private var moodList = ArrayList<Mood>() //This is the object that will contain a collection of Mood objects for display.

    private lateinit var currentDailyMood: DailyMood //Variable to store the daily mood.

    private var dailyComment: String = "" //Comment is initialized to an empty string.
    private var dailyMoodList = ArrayList<DailyMood>() //Array list to store
    private val MAX_HISTORY_SIZE = 7 //Set the maximum number of entries to record.
    var enableTouchEvents: Boolean = true //Variable to keep track of whether to allow touch event.

    private val KEY_DAILY_MOOD: String = "KEY_DAILY_MOOD" //Key to store daily mood in SharedPrefernces.
    private val KEY_DAILY_MOOD_LIST: String = "KEY_DAILY_MOOD_LIST" //Key to store mood list in SharedPreferences.
    private val KEY_COMMENT: String = "KEY_COMMENT" //Key to store the comment in SharedPrefences.
    lateinit var dailyMoodData: String //This variable stores the serialized JSON of the object.
    lateinit var dailyMoodListData: String //This variable stores the moodList as a JSON string.

    //onCreate() function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeObjects()
        loadMoodList()
        loadDailyMood()
        loadComment()
        foo(convertDate(Date()))

        //Set the click listener for the fab to navigate to the MoodHistoryActivity.
        root_frame_layout.fab_mood_history.setOnClickListener { v: View ->
            val intent = Intent(this, MoodHistoryActivity::class.java)
            intent.putExtra(KEY_DAILY_MOOD_LIST, dailyMoodList as Serializable)
            startActivity(intent)
        }

        //Set the click listener for the comment fab that will trigger the generation of the dialog.
        root_frame_layout.fab_add_comment.setOnClickListener { v: View ->
            createCommentDialogue()
        }
    }

    //Date is verified and daily mood saved when the activity is paused.
    override fun onPause() {
        foo(convertDate(Date()))
        saveDailyMoodToSharedPreferences(preferences, KEY_DAILY_MOOD, currentDailyMood) //Save current mood whenever the activity is paused.
        super.onPause()
    }

    //Data is loaded when the activity is restarted and the date is checked.
    override fun onRestart() {
        loadDailyMood() // Load here. Needs to be loaded before user interacts with MainActivity.
        loadComment() //Load here before user can interact with Activity.
        foo(convertDate(Date()))
        super.onRestart()
    }

    //Moodlist is loaded when the activity is resumed and the date is checked.
    override fun onResume() {
        loadMoodList()
        foo(convertDate(Date()))
        super.onResume()
    }

    //Create the menu for the Activity.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = getMenuInflater()
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Handles the action when the user clicks on Menu option.
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            R.id.menu_item_share_mood -> {
                val intent = Intent(Intent.ACTION_SEND)
                val sharedText = "Hi! ${currentDailyMood.mDescription} ${currentDailyMood.mComment}"
                sharedText.trim() //Clean up trailing white space.
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_TEXT, sharedText)
                startActivity(Intent.createChooser(intent, "Share using: "))
                return true
            }
            R.id.menu_item_delete_history -> { //Clear the list and save it.
                dailyMoodList.clear()
                saveArrayListToSharedPreferences(preferences, KEY_DAILY_MOOD_LIST, dailyMoodList)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    //This function will help disable touch events so the user can't click like crazy.
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return (enableTouchEvents && super.dispatchTouchEvent(ev))
    }

    //This function creates the dialog.
    private fun createCommentDialogue() {
        foo(convertDate(Date()))
        var commentText: EditText = EditText(this)
        var dialog = AlertDialog.Builder(this)
        dialog.setView(commentText)
        dialog.setTitle("How are you feeling?")
                .setMessage("Enter a comment.")
                .setNegativeButton("Cancel", null)
        dialog.setPositiveButton("OK") { dialog, button ->
            dailyComment = commentText.text.toString()
            dailyComment.trim() //Clean up trailing white space.
            currentDailyMood.mComment = dailyComment
            saveCommentToSharedPrefeences(preferences, KEY_COMMENT, dailyComment)
            saveDailyMoodToSharedPreferences(preferences, KEY_DAILY_MOOD, currentDailyMood)
        }
        dialog.create()
        dialog.show()
    }

    //This is the function we want called when the user clicks on a mood in the list.
    private fun moodItemClicked(mood: Mood) {
        dailyComment = ""
        foo(convertDate(Date()))
        enableTouchEvents = false
        Handler().postDelayed({
            enableTouchEvents = true
        }, 2000) // LENGTH_SHORT is usually 2 second long

        val message = "The current mood has been set to: ${mood.mDescription}"
        currentDailyMood.mDescription = mood.mDescription
        currentDailyMood.mImageId = mood.mImageId
        currentDailyMood.mBackgoundColor = mood.mBackgoundColor
        currentDailyMood.mComment = dailyComment
        saveDailyMoodToSharedPreferences(preferences, KEY_DAILY_MOOD, currentDailyMood)
        createToast(applicationContext, message)
    }

    //This function will initialize the state of the objects.
    fun initializeObjects() {
        preferences = getPreferences(Context.MODE_PRIVATE)
        generateMoodSelectionList() //Function that populates the ArrayList with Mood objects.
        layoutManager = LinearLayoutManager(this) //Our layoutManager holds an instance of a LinearLayoutManager
        recycler_view.layoutManager = layoutManager //Attach the layout manager to the recycler_view.
        adapter = MoodRecyclerAdapter(moodList, { mood: Mood -> moodItemClicked(mood) }) //Initialize our adapter variable with a MoodReyclerAdapter object. We pass in our data as a paramater.
        recycler_view.adapter = adapter //Set the adapter property of the recycler_view to the adapter we just created.
    }

    //Function loads moodList
    fun loadMoodList() {
        dailyMoodListData = getStringFromSharedPreferences(preferences, KEY_DAILY_MOOD_LIST)
        if (dailyMoodListData != "") {
            var gson = Gson() //Create a Gson object instance.
            val type = object : TypeToken<ArrayList<DailyMood>>() {
            }.type //Declare the type of object that should be restored.
            dailyMoodList = gson.fromJson(dailyMoodListData, type) //Restore the object from Json
        } else dailyMoodList = ArrayList() //Reinitialize if there is nothing loaded.
    }

    //Load the daily mood from shared preferences. If none is saved, a default mood is set.
    fun loadDailyMood() {
        dailyMoodData = getStringFromSharedPreferences(preferences, KEY_DAILY_MOOD)
        if (dailyMoodData == "") {
            generateDefaultDailyMood() //ensures that a default mood is made if none is saved.
        } else {
            var gson = Gson()
            currentDailyMood = gson.fromJson(dailyMoodData, DailyMood::class.java)
        }
    }

    //Load the comment from SharedPreferences.
    fun loadComment() {
        dailyComment = getStringFromSharedPreferences(preferences, KEY_COMMENT)
    }

    //Thus function checks current date against the object's date and adds to the list if they don't match.
    fun foo(todaysDate:String ): Boolean {
        //If the dates don't align, the day has changed. Add the the currentDailyMood to the list. Then,
        //reset the dailyMood object.
        if (!currentDailyMood.mDate.equals(todaysDate)) {
            dailyMoodList.add(DailyMood(currentDailyMood.mDescription, currentDailyMood.mImageId, currentDailyMood.mBackgoundColor, currentDailyMood.mComment, currentDailyMood.mDate))
            dailyMoodList = cleanArrayList(MAX_HISTORY_SIZE, dailyMoodList)
            saveArrayListToSharedPreferences(preferences, KEY_DAILY_MOOD_LIST, dailyMoodList)
            generateDefaultDailyMood()
            return true
        } else return false
    }

    //This is the function that generates the moods the user can select.
    fun generateMoodSelectionList() {
        moodList.add(Mood(getString(R.string.mood_happy), R.mipmap.smiley_happy, R.color.color_happy))
        moodList.add(Mood(getString(R.string.mood_super_happy), R.mipmap.smiley_super_happy, R.color.color_super_happy))
        moodList.add(Mood(getString(R.string.mood_normal), R.mipmap.smiley_normal, R.color.color_normal))
        moodList.add(Mood(getString(R.string.mood_disappointed), R.mipmap.smiley_disappointed, R.color.color_disappointed))
        moodList.add(Mood(getString(R.string.mood_sad), R.mipmap.smiley_sad, R.color.color_sad))
    }

    //This function generates the defaultMood.
    fun generateDefaultDailyMood() {
        val date = Date()
        dailyComment = ""
        currentDailyMood = DailyMood(getString(R.string.mood_happy), R.mipmap.smiley_happy, R.color.color_happy, dailyComment, convertDate(date))
        saveDailyMoodToSharedPreferences(preferences, KEY_DAILY_MOOD, currentDailyMood)
        saveCommentToSharedPrefeences(preferences, KEY_COMMENT, dailyComment)
    }
}