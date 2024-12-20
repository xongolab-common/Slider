package com.example.slider

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.slider.databinding.ActivityMainBinding
import com.example.slider.databinding.ActivityTripPlanningBinding
import com.example.slider.position_slider.PositionActivity
import com.example.slider.trip_planning_viewpager.TripPlanningActivity
import com.example.slider.trip_planning_viewpager.TripPlanningAdapter
import com.example.slider.up_down_slider.UpDownSliderActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpDownSlider.setOnClickListener {
            startActivity(Intent(this, UpDownSliderActivity::class.java))
        }

        binding.btnTripPlanning.setOnClickListener {
            startActivity(Intent(this, TripPlanningActivity::class.java))
        }

        binding.btnPosition.setOnClickListener {
            startActivity(Intent(this, PositionActivity::class.java))
        }


    }
}