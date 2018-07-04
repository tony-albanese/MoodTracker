package com.example.tony_albanese.moodtracker.controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.tony_albanese.moodtracker.R
import com.example.tony_albanese.moodtracker.model.Mood
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    private var moodList =  ArrayList<Mood>(); //This is the object that will contain a collection of Mood objects for display.
    private var layoutManager: RecyclerView.LayoutManager? = null //Create a reference to the layout manager that will organize the views in the RecyclerAdapter.
    private var adapter: RecyclerView.Adapter<MoodRecyclerAdapter.ViewHolder>? = null //Create a reference to our custom adapter.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generateMoodSelectionList() //Function that populates the ArrayList with Mood objects.

        layoutManager = LinearLayoutManager(this) //Our layoutManager holds an instance of a LinearLayoutManager
        recycler_view.layoutManager = layoutManager //Attach the layout manager to the recycler_view.

        adapter = MoodRecyclerAdapter(moodList, {mood: Mood -> moodItemClicked(mood)}) //Initialize our adapter variable with a MoodReyclerAdapter object. We pass in our data as a paramater.
        recycler_view.adapter = adapter //Set the adapter property of the recycler_view to the adapter we just created.

        //Set the click listener for the fab to navigate to the MoodHistoryActivity.
        root_frame_layout.fab_mood_history.setOnClickListener { v: View ->
            val intent: Intent = Intent(this, MoodHistoryActivity::class.java)
            startActivity(intent)
        }
        //Set the click listener for the comment fab that will trigger the generation of the dialog.
        root_frame_layout.fab_add_comment.setOnClickListener {v: View ->
            createCommentDialogue()
        }
    }

    //This is the function that generates the moods the user can select.
    fun generateMoodSelectionList(){
        moodList.add(Mood(getString(R.string.mood_happy),R.mipmap.smiley_happy, R.color.color_happy))
        moodList.add(Mood(getString(R.string.mood_super_happy), R.mipmap.smiley_super_happy, R.color.color_super_happy))
        moodList.add(Mood(getString(R.string.mood_normal), R.mipmap.smiley_normal, R.color.color_normal))
        moodList.add(Mood(getString(R.string.mood_disappointed), R.mipmap.smiley_disappointed, R.color.color_disappointed))
        moodList.add(Mood(getString(R.string.mood_sad), R.mipmap.smiley_sad, R.color.color_sad))
    }

    //This function creates the dialog.
    fun createCommentDialogue(){
        val commentText: EditText = EditText(this)
        //TODO: Format the EditText object
        //TODO: Add positive button.
        val dialog = AlertDialog.Builder(this)
        dialog.setView(commentText)
        dialog.setTitle("How are you feeling?")
                .setMessage("Enter a comment.")
                .setNegativeButton("Cancel",null)
                .create()

        dialog.show()
    }

    //This is the function we want called when the user clicks on a mood in the list.
    private fun moodItemClicked(mood: Mood){
        Toast.makeText(this, "Clicked: ${mood.mDescription }.", Toast.LENGTH_SHORT).show()
    }

}
