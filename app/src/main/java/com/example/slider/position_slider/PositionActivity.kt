package com.example.slider.position_slider

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.slider.R
import com.example.slider.databinding.ActivityMainBinding
import com.example.slider.databinding.ActivityPositionBinding
import com.example.slider.databinding.AlertDialogTutorialBinding

class PositionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPositionBinding
    private lateinit var tutorialViewPagerAdapter: TutorialViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tutorialAlertDialog()
    }

    private fun tutorialAlertDialog() {

        val dialogBinding =
            AlertDialogTutorialBinding.inflate(LayoutInflater.from(this))

        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(dialogBinding.root)

        val alertDialog: AlertDialog?
        alertDialog = alertDialogBuilder.create()
        alertDialog.show()

        setUpViewPager(dialogBinding, alertDialog)

        dialogBinding.apply {
            llAccount.background = ContextCompat.getDrawable(this@PositionActivity, R.drawable.dr_white_fill_10)
            llPublicCharging.background = ContextCompat.getDrawable(this@PositionActivity, R.drawable.dr_white_fill_10)

            rlNext.setOnClickListener {
                if (tutorialViewPager.currentItem < tutorialViewPagerAdapter.itemCount - 1) {
                    tutorialViewPager.currentItem++
                } else {
                    alertDialog.dismiss()
                    onBackPressed()
                }
            }

            tvSkip.setOnClickListener {
                alertDialog.dismiss()
                onBackPressed()
            }

        }

        alertDialog.window!!.setLayout((resources.displayMetrics.widthPixels * 0.9).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)

    }


    private fun setUpViewPager(dialogBinding: AlertDialogTutorialBinding, alertDialog: AlertDialog?) {

        val title = arrayOf(
          "LBL_PUBLIC_CHARGING_1_TITLE",
          "LBL_PUBLIC_CHARGING_2_TITLE",
          "LBL_PUBLIC_CHARGING_3_TITLE",
          "LBL_PUBLIC_CHARGING_4_TITLE",
        )

        val description = arrayOf(
          "LBL_PUBLIC_CHARGING_1_SUB_TITLE",
          "LBL_PUBLIC_CHARGING_2_SUB_TITLE",
          "LBL_PUBLIC_CHARGING_3_SUB_TITLE",
          "LBL_PUBLIC_CHARGING_4_SUB_TITLE",
        )


        tutorialViewPagerAdapter = TutorialViewPagerAdapter(this, title, description)
        dialogBinding.tutorialViewPager.adapter = tutorialViewPagerAdapter

        dialogBinding.indicator.attachTo(dialogBinding.tutorialViewPager)

        dialogBinding.rlNext.setOnClickListener {
            if (dialogBinding.tutorialViewPager.currentItem < tutorialViewPagerAdapter.itemCount - 1) {
                dialogBinding.tutorialViewPager.currentItem++
            }
        }
        dialogBinding.btnBack.setOnClickListener {
            if (dialogBinding.tutorialViewPager.currentItem > 0) {
                dialogBinding.tutorialViewPager.currentItem--
            }
        }

        dialogBinding.tutorialViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dialogBinding.btnBack.visibility = if (position == 0) View.INVISIBLE else View.VISIBLE

                if (position == tutorialViewPagerAdapter.itemCount - 1) {
                    dialogBinding.btnText.text = "Done"
                } else {
                    dialogBinding.btnText.text =  "Next"

                }

                val layoutParams = alertDialog!!.window!!.attributes

                when (position) {
                    0 -> {
                        layoutParams.gravity = Gravity.BOTTOM
                        layoutParams.y = 300
                        alertDialog.window!!.attributes = layoutParams

                        binding.llQRCode.background = ContextCompat.getDrawable(this@PositionActivity, R.drawable.dr_button_primary_bg_10)
                        binding.llVehicle.background = null
                    }
                    1 -> {
                        layoutParams.gravity = Gravity.TOP
                        layoutParams.y = 170
                        alertDialog.window!!.attributes = layoutParams
                        binding.llQRCode.background = null
                        binding.llVehicle.background = ContextCompat.getDrawable(this@PositionActivity, R.drawable.dr_button_primary_bg_10)

                    }
                    2 -> {
                        layoutParams.gravity = Gravity.CENTER
                        alertDialog.window!!.attributes = layoutParams

                        binding.llQRCode.background = null
                        binding.llVehicle.background = null
                    }
                    3 -> {
                        layoutParams.gravity = Gravity.CENTER
                        alertDialog.window!!.attributes = layoutParams

                        binding.llQRCode.background = null
                        binding.llVehicle.background = null
                    }
                }
            }
        })
    }

}