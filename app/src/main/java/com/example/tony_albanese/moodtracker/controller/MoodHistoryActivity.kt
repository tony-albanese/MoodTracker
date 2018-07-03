package com.example.tony_albanese.moodtracker.controller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.tony_albanese.moodtracker.MoodHistoryRecyclerAdapter
import com.example.tony_albanese.moodtracker.R
import kotlinx.android.synthetic.main.activity_mood_history.*

class MoodHistoryActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MoodHistoryRecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_history)

        layoutManager = LinearLayoutManager(this)
        mood_history_recycler_view.layoutManager = layoutManager

        adapter = MoodHistoryRecyclerAdapter()
        mood_history_recycler_view.adapter = adapter

    }
}
