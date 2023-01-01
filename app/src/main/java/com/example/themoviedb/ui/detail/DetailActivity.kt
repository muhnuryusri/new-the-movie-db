package com.example.themoviedb.ui.detail

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.themoviedb.BuildConfig.IMAGE_URL
import com.example.themoviedb.R
import com.example.themoviedb.adapter.ReviewsAdapter
import com.example.themoviedb.databinding.ActivityDetailBinding
import com.example.themoviedb.ui.ViewModelFactory
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment


class DetailActivity : AppCompatActivity() {
    private lateinit var adapter: ReviewsAdapter
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding
    val apiKey =  "AIzaSyAKnuxyAWfejQ8fo0dsCap2y03gXupdrSQ"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_DATA, 0)

            detailViewModel.getDetailMovie(movieId).observe(this) { movie ->
                with(binding) {
                    Glide.with(this@DetailActivity)
                        .load(IMAGE_URL + movie.backdropPath)
                        .centerCrop()
                        .apply(RequestOptions().override(510, 300))
                        .into(binding.imgBanner)

                    tvTitle.text = movie.title
                    tvDuration.text = movie.runtime.toString() + " minutes"
                    tvOverview.text = movie.overview
                }
            }

            detailViewModel.getReviews(movieId).observe(this) { review ->
                if (review.isEmpty()) {
                    binding.tvNoReview.visibility = View.VISIBLE
                } else {
                    binding.tvNoReview.visibility = View.GONE
                    binding.rvReviews.adapter?.let { adapter ->
                        when(adapter) {
                            is ReviewsAdapter -> adapter.setData(review)
                        }
                    }
                }
            }

            detailViewModel.getTrailer(movieId).observe(this) { trailer ->
                val ytFragment =
                    fragmentManager.findFragmentById(R.id.yt_trailer) as YouTubePlayerFragment
                ytFragment.initialize(apiKey, object : YouTubePlayer.OnInitializedListener{
                    override fun onInitializationSuccess(
                        provider: YouTubePlayer.Provider?,
                        player: YouTubePlayer?,
                        p2: Boolean
                    ) {
                        player?.cueVideo(trailer.key)
                        player?.play()
                    }

                    override fun onInitializationFailure(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubeInitializationResult?
                    ) {
                        Toast.makeText(this@DetailActivity , "Video player Failed" , Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

        showRecyclerView()
    }

    private fun showRecyclerView() {
        adapter = ReviewsAdapter()

        binding.rvReviews.layoutManager = LinearLayoutManager(this)
        binding.rvReviews.adapter = adapter
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}