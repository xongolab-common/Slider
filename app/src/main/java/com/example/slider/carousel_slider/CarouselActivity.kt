package com.example.slider.carousel_slider

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.State
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.slider.R
import com.example.slider.databinding.ActivityCarouselBinding
import com.example.slider.databinding.RawBannerBinding


class CarouselActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarouselBinding
    private lateinit var viewPagerAdapter: CarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCarouselBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewPager()
    }

    private fun setUpViewPager() {
        setupCarousel()

        val resortList = arrayListOf<Int>().apply {
            add(R.drawable.ic_banner)
            add(R.drawable.ic_banner)
            add(R.drawable.ic_banner)
        }

        viewPagerAdapter = CarouselAdapter(this)
        viewPagerAdapter.addDrawableData(resortList)

        binding.viewPager.adapter = viewPagerAdapter
        binding.indicator.attachTo(binding.viewPager)

    }

    private fun setupCarousel() {

        binding.viewPager.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(com.intuit.sdp.R.dimen._10sdp)
        val currentItemHorizontalMarginPx = resources.getDimension(com.intuit.sdp.R.dimen._20sdp)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
        }
        binding.viewPager.setPageTransformer(pageTransformer)

        val itemDecoration = HorizontalMarginItemDecoration(
            this,
            com.intuit.sdp.R.dimen._20sdp
        )
        binding.viewPager.addItemDecoration(itemDecoration)


    }
}

class HorizontalMarginItemDecoration(context: Context, @DimenRes horizontalMarginInDp: Int) :
    ItemDecoration() {

    private val horizontalMarginInPx: Int =
        context.resources.getDimension(horizontalMarginInDp).toInt()

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: State
    ) {
        outRect.right = horizontalMarginInPx
        outRect.left = horizontalMarginInPx
    }

}

@SuppressLint("NotifyDataSetChanged")
class CarouselAdapter(var context: Context) : RecyclerView.Adapter<CarouselAdapter.Holder>() {

    var objList: ArrayList<String> = ArrayList()
    var drawableList: ArrayList<Int> = ArrayList()
    var onItemClick: ((position: Int) -> Unit)? = null

    inner class Holder(val binding: RawBannerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RawBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        if (objList.isNotEmpty()) {
            return objList.size
        } else if (drawableList.isNotEmpty()) {
            return drawableList.size
        }
        return 0
    }

    @SuppressLint("SetTextI18n")

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.apply {
            if (objList.isNotEmpty()) {
                val item = objList[position]
                Glide.with(context).load(item).into(ivBanner)
            } else {
                val drawable = drawableList[position]
                ivBanner.setImageResource(drawable)
            }
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    fun addData(mObj: ArrayList<String>) {
        objList = ArrayList()
        objList.addAll(mObj)
        this.notifyDataSetChanged()
    }

    fun addDrawableData(mObj: ArrayList<Int>) {
        drawableList = ArrayList()
        drawableList.addAll(mObj)
        this.notifyDataSetChanged()
    }
}