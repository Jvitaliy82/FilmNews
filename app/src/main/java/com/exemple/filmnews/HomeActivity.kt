package com.exemple.filmnews

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.exemple.filmnews.databinding.ActivityHomeBinding
import java.util.*

class HomeActivity : AppCompatActivity(), MovieAdapter.OnItemClickListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var lstSlides: List<Slide>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lstSlides = mutableListOf(
            Slide(R.drawable.sl1, "Рассомаха"),
            Slide(R.drawable.sl2, "Странные дела"),
            Slide(R.drawable.sl1, "Рассомаха"),
            Slide(R.drawable.sl2, "Странные дела")
        )

        binding.sliderPager.adapter = SliderPageAdapter(this, lstSlides)
        binding.indicator.setupWithViewPager(binding.sliderPager, true)

        val timer = Timer()
        timer.scheduleAtFixedRate( this@HomeActivity.SliderTimer(), 4000, 6000)

        val lstMovies = mutableListOf(
            Movie(title = "Moana", thumbnail = R.drawable.moana),
            Movie(title = "Холодное сердце", thumbnail = R.drawable.cold),
            Movie(title = "Монстры", thumbnail = R.drawable.monsters),
            Movie(title = "Райя", thumbnail = R.drawable.raya),
            Movie(title = "Чудовище", thumbnail = R.drawable.ugly),
            Movie(title = "Вверх", thumbnail = R.drawable.up)
        )

        val adapter = MovieAdapter(this)
        adapter.submitList(lstMovies)
        binding.rvMoves.adapter = adapter

    }

    override fun onItemClick(movie: Movie, movieImageView: ImageView) {
        val intent = Intent(this, MovieDetailActivity::class.java).apply {
            putExtra("title", movie.title)
            putExtra("imageURL", movie.thumbnail)
        }

        val options = ActivityOptions.makeSceneTransitionAnimation(this@HomeActivity, movieImageView, "sharedName")
        startActivity(intent, options.toBundle())
        Toast.makeText(this, "Click: ${movie.title}", Toast.LENGTH_SHORT).show()
    }

    inner class SliderTimer: TimerTask() {
        override fun run() {
            this@HomeActivity.runOnUiThread {
                if (binding.sliderPager.currentItem < lstSlides.size - 1) {
                    binding.sliderPager.setCurrentItem(binding.sliderPager.currentItem + 1)
                } else {
                    binding.sliderPager.setCurrentItem(0)
                }
            }
        }

    }
}