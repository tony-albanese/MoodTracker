package com.example.tony_albanese.moodtracker.controller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.tony_albanese.moodtracker.R
import com.example.tony_albanese.moodtracker.model.DailyMood
import kotlinx.android.synthetic.main.activity_mood_history.*

//This activity is responsible for displaying the user's mood history.
class MoodHistoryActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null //Need a layout manager for the recycler view.
    private var adapter: RecyclerView.Adapter<MoodHistoryRecyclerAdapter.ViewHolder>? = null //Adapter for the recycler view.
    var moodHistoryList = ArrayList<DailyMood>() //The array list that will hold the history.
    val KEY_DAILY_MOOD_LIST: String = "KEY_DAILY_MOOD_LIST" //The Key to retrieving the list from the intent.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_history)

        //Check if the intent has any extra data. If it does, load it.
        if (intent.hasExtra(KEY_DAILY_MOOD_LIST)) {
            moodHistoryList = intent.extras.get(KEY_DAILY_MOOD_LIST) as ArrayList<DailyMood>
        }

        layoutManager = LinearLayoutManager(this)
        mood_history_recycler_view.layoutManager = layoutManager

        adapter = MoodHistoryRecyclerAdapter(moodHistoryList)
        mood_history_recycler_view.adapter = adapter

    }
}
