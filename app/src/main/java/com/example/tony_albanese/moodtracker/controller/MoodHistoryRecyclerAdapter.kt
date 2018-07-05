package com.example.tony_albanese.moodtracker.controller

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.tony_albanese.moodtracker.R
import com.example.tony_albanese.moodtracker.model.DailyMood


class MoodHistoryRecyclerAdapter(val dailyMoodList: ArrayList<DailyMood>) : RecyclerView.Adapter<MoodHistoryRecyclerAdapter.ViewHolder>() {


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
        return dailyMoodList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val currentDailyMood = dailyMoodList[i]
        viewHolder.historyDateTextView.text = currentDailyMood.mDate
        viewHolder.historyDescriptionTextView.text = currentDailyMood.mDescription
            if(!currentDailyMood.mComment[i].toString().isNullOrBlank()){
                viewHolder.historyDescriptionTextView.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.ic_comment_black_48px, 0)
                viewHolder.historyDescriptionTextView.setOnClickListener { v: View ->
                    var comment = currentDailyMood.mComment
                    makeCommentToast(v, comment)
                }
            }

        viewHolder.historyImageView.setImageResource(currentDailyMood.mImageId)
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