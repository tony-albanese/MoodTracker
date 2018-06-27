package com.example.tony_albanese.moodtracker

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
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
        }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}