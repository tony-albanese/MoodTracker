package com.example.tony_albanese.moodtracker.controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.tony_albanese.moodtracker.R
import com.example.tony_albanese.moodtracker.model.Mood
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    private var moodList =  ArrayList<Mood>();
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MoodRecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generateMoodSelectionList()

        layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager

        adapter = MoodRecyclerAdapter(moodList)
        recycler_view.adapter = adapter

        //Set the click listener for the fab to navigate to the MoodHistoryActivity.

        root_frame_layout.fab_mood_history.setOnClickListener { v: View ->
            val intent: Intent = Intent(this, MoodHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    fun generateMoodSelectionList(){
        moodList.add(Mood(getString(R.string.mood_happy),R.mipmap.smiley_happy, 0))
        moodList.add(Mood(getString(R.string.mood_super_happy), R.mipmap.smiley_super_happy, 0))
        moodList.add(Mood(getString(R.string.mood_normal), R.mipmap.smiley_normal, 0))
        moodList.add(Mood(getString(R.string.mood_disappointed), R.mipmap.smiley_disappointed, 0))
        moodList.add(Mood(getString(R.string.mood_sad), R.mipmap.smiley_sad, 0))
    }
}
