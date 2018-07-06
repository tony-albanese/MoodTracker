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
    var moodHistoryList = ArrayList<DailyMood>();
    var testlist = ArrayList<DailyMood>()
    val KEY_DAILY_MOOD_LIST: String = "DAILY_MOOD_LIST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_history)

        //moodHistoryList = intent.extras.get(KEY_DAILY_MOOD_LIST) as ArrayList<DailyMood>
        //moodHistoryList = MainActivity::dailyMoodList as ArrayList<DailyMood>
        populateTestList()

        layoutManager = LinearLayoutManager(this)
        mood_history_recycler_view.layoutManager = layoutManager

        adapter = MoodHistoryRecyclerAdapter(testlist)
        mood_history_recycler_view.adapter = adapter

    }

    private fun populateTestList(){
        testlist.add(DailyMood("Mood 1", R.mipmap.smiley_happy, R.color.color_happy, "Comment 1", "Date 1"))
        testlist.add(DailyMood("Mood 1", R.mipmap.smiley_happy, R.color.color_happy, "Comment 1", "Date 1"))
        testlist.add(DailyMood("Mood 2", R.mipmap.smiley_sad, R.color.color_sad, "Comment 2", "Date 2"))
    }

}
