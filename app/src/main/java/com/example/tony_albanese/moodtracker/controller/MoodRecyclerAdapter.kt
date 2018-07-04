package com.example.tony_albanese.moodtracker.controller

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tony_albanese.moodtracker.R
import com.example.tony_albanese.moodtracker.model.Mood

/* This class defines the RecyclerView Adapter object that will handle the moods the user can select from. This is NOT the adapter for the moods the user enters.*/
//The MoodRecyclerAdapter acceps an ArrayList of Mood objects as it's parameter as well as a function with signature that accepts a Mood as a parameter and no return.
class MoodRecyclerAdapter(val moodList: ArrayList<Mood>, val clickListener: (Mood) -> Unit) : RecyclerView.Adapter<MoodRecyclerAdapter.ViewHolder>() {

    //TODO: Implemenet clickListener here.
    //The ViewHolder class defines the views from the CardLayout that will be "held" and populated in the RecyclerView.

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageItem: ImageView
        var itemText: TextView

        init {
            imageItem = itemView.findViewById(R.id.card_mood_image)
            itemText = itemView.findViewById(R.id.card_mood_text)
        }

    }

    //This method inflates the layout into a ViewHolder that contains the CardView. The views to populated are children of the ViewHolder.
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.mood_card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    //This function returns the number of items that need to be displayed.
    override fun getItemCount(): Int {
        return moodList.size
    }

    //This function binds the data from the ArrayList to the views held in the ViewHolder object.
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val mood = moodList[i]
        viewHolder.itemText.text = mood.mDescription
        viewHolder.imageItem.setImageResource(mood.mImageId)
        viewHolder.imageItem.setBackgroundResource(mood.mBackgoundColor)
        viewHolder.imageItem.setOnClickListener {clickListener(mood)}
    }




}