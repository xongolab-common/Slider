package com.example.slider.trip_planning_viewpager

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import com.example.slider.R
import com.example.slider.databinding.ActivityTripPlanningBinding

class TripPlanningActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityTripPlanningBinding
    private lateinit var tripPlanningAdapter: TripPlanningAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTripPlanningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSkip.setOnClickListener(this)
        setUpViewPager()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.rlBtnNext -> {
                binding.tripViewPager.currentItem++
            }
            R.id.tvSkip ->{
                onBackPressed()
            }
        }
    }


    private fun setUpViewPager() {
        val title = arrayOf("LBL_TRIP_PLANNING_1", "LBL_TRIP_PLANNING_2", "LBL_TRIP_PLANNING_3")

        val description = arrayOf("LBL_TRIP_PLANNING_SUB_TITLE_1", "LBL_TRIP_PLANNING_SUB_TITLE_2", "LBL_TRIP_PLANNING_SUB_TITLE_3")

        tripPlanningAdapter = TripPlanningAdapter(this, title, description)

        binding.tripViewPager.adapter = tripPlanningAdapter
        binding.tripViewPager.currentItem = 0
        binding.indicator.isSaveFromParentEnabled = false
        binding.indicator.setViewPager(binding.tripViewPager)

        binding.rlBtnNext.setOnClickListener(this)

        binding.tripViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            private val thresholdOffset = 0.5f
            private var scrollStarted = false
            private var checkDirection = false

            override fun onPageScrolled(
                position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {
                val lastIndex: Int = tripPlanningAdapter.count - 1
                val currentPage: Int = binding.tripViewPager.currentItem
                if (checkDirection) {

                    if (thresholdOffset > positionOffset && lastIndex == currentPage) {
                        Log.e("Left", "going left")
                    } else {
                        Log.e("Right", "going right")
                    }

                    checkDirection = false
                }
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.rlBtnNext.visibility = View.VISIBLE
                        binding.llTwoButton.visibility = View.GONE
                        binding.rlBtnNext.setOnClickListener {
                            binding.tripViewPager.currentItem++
                        }
                    }

                    1 -> {
                        binding.rlBtnNext.visibility = View.GONE
                        binding.llTwoButton.visibility = View.VISIBLE
                        binding.rlNext.setOnClickListener {
                            binding.tripViewPager.currentItem++
                        }

                        binding.btnBack.setOnClickListener {
                            binding.tripViewPager.currentItem--
                        }
                    }

                    2 -> {
                        binding.rlBtnNext.visibility = View.GONE
                        binding.llTwoButton.visibility = View.VISIBLE
                        binding.rlNext.setOnClickListener {
                            finish()
                        }

                        binding.btnBack.setOnClickListener {
                            binding.tripViewPager.currentItem--
                        }
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (!scrollStarted && state == ViewPager.SCROLL_STATE_DRAGGING) {
                    scrollStarted = true
                    checkDirection = true
                } else {
                    scrollStarted = false
                }
            }

        })
    }
}