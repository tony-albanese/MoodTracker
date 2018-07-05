package com.example.tony_albanese.moodtracker.controller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.tony_albanese.moodtracker.R
import com.example.tony_albanese.moodtracker.model.DailyMood
import kotlinx.android.synthetic.main.activity_mood_history.*

class MoodHistoryActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MoodHistoryRecyclerAdapter.ViewHolder>? = null
    var dailyMoodList = ArrayList<DailyMood>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_history)

        //generateSamplyDummyMoodList()

        layoutManager = LinearLayoutManager(this)
        mood_history_recycler_view.layoutManager = layoutManager

        adapter = MoodHistoryRecyclerAdapter(dailyMoodList)
        mood_history_recycler_view.adapter = adapter

    }

    /* Leave out the dummy data for now.
    //This is a temporary function to generate the sample moods.
    fun generateSamplyDummyMoodList(){
        dailyMoodList.add(DailyMood(getString(R.string.mood_sad), R.mipmap.smiley_sad, R.color.color_sad, "This is hard work.", "5/5/2010"))

        dailyMoodList.add(DailyMood(getString(R.string.mood_happy), R.mipmap.smiley_happy, R.color.color_happy, "I am making progress.", "5/9/2010"))
    }*/
}
