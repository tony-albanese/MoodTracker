package com.example.tony_albanese.moodtracker.controller

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tony_albanese.moodtracker.R
import com.example.tony_albanese.moodtracker.model.DailyMood
import kotlinx.android.synthetic.main.mood_history_card_layout.view.*

//This custom class defines that adapter that will populate the RecyclerView with data.
class MoodHistoryRecyclerAdapter(val dailyMoodList: ArrayList<DailyMood>) : RecyclerView.Adapter<MoodHistoryRecyclerAdapter.ViewHolder>() {

    //This inner class creates the ViewHolder object that will contain our views to populate.
    inner class ViewHolder(moodHistoryItemView: View) : RecyclerView.ViewHolder(moodHistoryItemView) {
        //These variables are the views that will be populated.
        var historyDateTextView: TextView = moodHistoryItemView.mood_history_date
        var historyImageView: ImageView
        var historyDescriptionTextView: TextView
        var cardView: CardView

        init {
            historyImageView = moodHistoryItemView.mood_history_image
            historyDescriptionTextView = moodHistoryItemView.mood_history_description
            cardView = moodHistoryItemView.mood_history_card_view
        }
    }

    //Returns the size of the list.
    override fun getItemCount(): Int {
        return dailyMoodList.size
    }

    //This function binds the data from the list to views in the view holder.
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val currentDailyMood = dailyMoodList[i]
        viewHolder.historyDateTextView.text = currentDailyMood.mDate
        viewHolder.historyDescriptionTextView.text = currentDailyMood.mDescription
        if (!currentDailyMood.mComment.isBlank()) {
            viewHolder.historyDescriptionTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_comment_black_48px, 0)
            viewHolder.historyDescriptionTextView.setOnClickListener { v: View ->
                val comment = currentDailyMood.mComment
                createToast(v.context, comment)
            }
        }

        viewHolder.historyImageView.setImageResource(currentDailyMood.mImageId)

        //This line converts the backgroundColor value to an actual android color object.
        val color = ContextCompat.getColor(viewHolder.cardView.context, currentDailyMood.mBackgoundColor)
        viewHolder.cardView.setCardBackgroundColor(color)
    }

    //Function inflates the layout and returns the ViewHolder.
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.mood_history_card_layout, viewGroup, false)
        return ViewHolder(v)
    }

}