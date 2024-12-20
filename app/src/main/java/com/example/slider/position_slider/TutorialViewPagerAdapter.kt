package com.example.slider.position_slider

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.slider.databinding.RawHomeTutorialBinding


class TutorialViewPagerAdapter(
    private val context: Context,
    private val title: Array<String>,
    private val description: Array<String>,
) : RecyclerView.Adapter<TutorialViewPagerAdapter.TutorialViewHolder>() {

    inner class TutorialViewHolder(val binding: RawHomeTutorialBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialViewHolder {
        val binding = RawHomeTutorialBinding.inflate(LayoutInflater.from(context), parent, false)
        return TutorialViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TutorialViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = title[position]
            tvDescription.text = description[position]

            if (position == 2) {
                llStation.visibility = View.VISIBLE
                Log.d("TutorialAdapter", "llStation is VISIBLE for position $position")
            } else {
                llStation.visibility = View.GONE
                Log.d("TutorialAdapter", "llStation is GONE for position $position")
            }

        }
    }


    override fun getItemCount(): Int = title.size
}
