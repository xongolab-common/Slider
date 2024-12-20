package com.example.slider.up_down_slider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.slider.R
import com.example.slider.databinding.ActivityUpDownSliderBinding
import com.example.slider.up_down_slider.ViewPagerExtensions.addCarouselEffect
import com.google.android.material.tabs.TabLayoutMediator


class UpDownSliderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpDownSliderBinding
    private lateinit var sliderAdapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpDownSliderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sliderAdapter = SliderAdapter(this@UpDownSliderActivity)

        binding.viewPager.adapter = sliderAdapter
        binding.viewPager.currentItem = 0

        binding.viewPager.addCarouselEffect()

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()

        imageList()
    }

    private fun imageList() {
        sliderAdapter.objList = ArrayList()

        var model = ResortModel()
        model.sliderImage = R.drawable.ic_splash_bg
        sliderAdapter.objList.add(model)

        model = ResortModel()
        model.sliderImage = R.drawable.ic_splash_bg
        sliderAdapter.objList.add(model)

        model = ResortModel()
        model.sliderImage = R.drawable.ic_splash_bg
        sliderAdapter.objList.add(model)

        model = ResortModel()
        model.sliderImage = R.drawable.ic_splash_bg
        sliderAdapter.objList.add(model)

        model = ResortModel()
        model.sliderImage = R.drawable.ic_splash_bg
        sliderAdapter.objList.add(model)

        sliderAdapter.addData(sliderAdapter.objList)
    }

}

