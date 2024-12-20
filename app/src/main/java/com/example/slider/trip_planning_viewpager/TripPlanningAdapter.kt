package com.example.slider.trip_planning_viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.slider.R
import com.example.slider.databinding.RawTripPlanningBinding


class TripPlanningAdapter(
    var context: Context,
    var title: Array<String>,
    var description: Array<String>,

) : PagerAdapter(){

    override fun getCount(): Int {
        return 3
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = RawTripPlanningBinding.inflate(LayoutInflater.from(container.context), container, false)

        when (position) {
            0 -> {
                binding.llTrip1.visibility = View.VISIBLE
                binding.rlTrip3.visibility = View.GONE
                binding.tvTitle.text = title[position]
                binding.tvDescription.text = description[position]
                binding.icLocation1.setImageResource(R.drawable.ic_tutorial_location_1)
            }
            1 -> {
                binding.llTrip1.visibility = View.VISIBLE
                binding.rlTrip3.visibility = View.GONE
                binding.tvTitle.text = title[position]
                binding.tvDescription.text = description[position]
                binding.icLocation1.setImageResource(R.drawable.ic_tutorial_location_2)
            }
            2 -> {
                binding.llTrip1.visibility = View.GONE
                binding.rlTrip3.visibility = View.VISIBLE
                binding.tvTitle.text = title[position]
                binding.tvDescription.text = description[position]
                binding.icLocation1.setImageResource(R.drawable.ic_tutorial_location_2)

            }
        }

        container.addView(binding.root)
        return binding.root
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}



