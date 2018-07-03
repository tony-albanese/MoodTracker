package com.example.tony_albanese.moodtracker.controller

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tony_albanese.moodtracker.R

class MoodHistoryRecyclerAdapter : RecyclerView.Adapter<MoodHistoryRecyclerAdapter.ViewHolder>() {
    private var dates = arrayOf("Date 1", "Date 2", "Date 3")
    inner class ViewHolder(moodHistoryItemView: View): RecyclerView.ViewHolder(moodHistoryItemView){


        var historyDateTextView : TextView
        init {
            historyDateTextView = moodHistoryItemView.findViewById(R.id.mood_history_date)
        }
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.historyDateTextView.text = dates[i]
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.mood_history_card_layout, viewGroup, false)
        return ViewHolder(v)
    }
}