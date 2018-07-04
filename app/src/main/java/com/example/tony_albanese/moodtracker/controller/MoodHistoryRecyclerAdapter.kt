package com.example.tony_albanese.moodtracker.controller

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tony_albanese.moodtracker.R

class MoodHistoryRecyclerAdapter : RecyclerView.Adapter<MoodHistoryRecyclerAdapter.ViewHolder>() {
    private var dates = arrayOf("Date 1", "Date 2", "Date 3")
    private var dailyComments = arrayOf("I just found a job.", "My project works!", null)
    private var moodImages = intArrayOf(R.mipmap.smiley_sad, R.mipmap.smiley_normal, R.mipmap.smiley_super_happy)


    inner class ViewHolder(moodHistoryItemView: View): RecyclerView.ViewHolder(moodHistoryItemView){


        var historyDateTextView : TextView
        var historyImageView : ImageView
        var historyDescriptionTextView: TextView

        init {
            historyDateTextView = moodHistoryItemView.findViewById(R.id.mood_history_date)
            historyImageView = moodHistoryItemView.findViewById(R.id.mood_history_image)
            historyDescriptionTextView = moodHistoryItemView.findViewById(R.id.mood_history_description)
        }
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.historyDateTextView.text = dates[i]
        viewHolder.historyDescriptionTextView.text = "I'll pass a description hre."
        viewHolder.historyImageView.setImageResource(moodImages[i])
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.mood_history_card_layout, viewGroup, false)
        return ViewHolder(v)
    }
}