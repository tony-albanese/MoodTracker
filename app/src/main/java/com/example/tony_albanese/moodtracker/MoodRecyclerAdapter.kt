package com.example.tony_albanese.moodtracker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.tony_albanese.moodtracker.model.Mood

/* This class defines the RecyclerView Adapter object that will handle the moods the user can select from. This is NOT the adapter for the moods the user enters.*/
class MoodRecyclerAdapter(val moodList: ArrayList<Mood>) : RecyclerView.Adapter<MoodRecyclerAdapter.ViewHolder>() {

    //Will include some dummy data here that will NOT be included in the final project.
    private val moods = arrayOf("Feeling Great!", "Feeling OK", "Feeling sad.")
    private val images = intArrayOf(R.mipmap.smiley_super_happy, R.mipmap.smiley_normal, R.mipmap.smiley_sad)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageItem: ImageView
        var itemText: TextView

        init {
            imageItem = itemView.findViewById(R.id.card_mood_image)
            itemText = itemView.findViewById(R.id.card_mood_text)

            /* This sets the onClickListener for each item in the RecyclerView.*/
            // TODO: Implement a method to set the daily mood when the user taps on a card.
            itemView.setOnClickListener { v: View ->
                var position: Int = adapterPosition
                val textView = v.findViewById<TextView>(R.id.card_mood_text)

                val toast = Toast.makeText(v.context, textView.text, Toast.LENGTH_SHORT)
                toast.show();

            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.mood_card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return moodList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val mood = moodList[i]
        viewHolder.itemText.text = mood.mDescription
        viewHolder.imageItem.setImageResource(images[i])
    }
}