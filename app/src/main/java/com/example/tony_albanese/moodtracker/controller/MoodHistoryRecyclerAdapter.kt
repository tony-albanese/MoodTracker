package com.example.tony_albanese.moodtracker.controller

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.tony_albanese.moodtracker.R
import java.util.*


class MoodHistoryRecyclerAdapter() : RecyclerView.Adapter<MoodHistoryRecyclerAdapter.ViewHolder>() {
    var day = Date()
    private var dates = arrayOf(convertDate(day), convertDate(day), convertDate(day))
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
        viewHolder.historyDescriptionTextView.text = "I'll pass a description here."

            if(!dailyComments[i].isNullOrBlank()){
                viewHolder.historyDescriptionTextView.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.ic_comment_black_48px, 0)
                viewHolder.historyDescriptionTextView.setOnClickListener { v: View ->
                    var comment = dailyComments[i].toString()
                    makeCommentToast(v, comment)
                }
            }

        viewHolder.historyImageView.setImageResource(moodImages[i])
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.mood_history_card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    fun makeCommentToast(view: View, comment: String){
        val toast = Toast.makeText(view.context, comment, Toast.LENGTH_SHORT)
        toast.show();
    }
}