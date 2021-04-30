package com.exemple.filmnews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class SliderPageAdapter(val context: Context, val list: List<Slide>): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.slide_item, null)

        val image = view.findViewById<ImageView>(R.id.imageSlider)
        val title = view.findViewById<TextView>(R.id.slide_title)
        image.setImageResource(list[position].image)
        title.text = list[position].title
        container.addView(view)
        return view
    }

    override fun getCount() = list.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}