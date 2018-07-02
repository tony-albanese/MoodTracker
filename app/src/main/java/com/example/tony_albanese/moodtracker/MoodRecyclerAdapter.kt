package com.example.tony_albanese.moodtracker

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/* This class defines the RecyclerView Adapter object that will handle the moods the user can select from. This is NOT the adapter for the moods the user enters.*/
class MoodRecyclerAdapter : RecyclerView.Adapter<MoodRecyclerAdapter.ViewHolder>(){

    //Will include some dummy data here that will NOT be included in the final project.
    private val moods = arrayOf("Feeling Great!", "Feeling OK" )
    private val images = intArrayOf(R.mipmap.smiley_super_happy, R.mipmap.smiley_normal)

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            //Empty
            var imageItem: ImageView
            var itemText: TextView

            init {
                imageItem = itemView.findViewById(R.id.card_mood_image)
                itemText = itemView.findViewById(R.id.card_mood_text)

                itemView.setOnClickListener{ v: View ->
                    var position: Int = adapterPosition
                    Snackbar.make(v, moods[position], Snackbar.LENGTH_LONG).setAction("Action", null).show()


                }
            }
        }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.mood_card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return moods.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemText.text = moods[i]
        viewHolder.imageItem.setImageResource(images[i])
    }
}