package com.example.themoviedb.ui.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviedb.adapter.MoviesAdapter
import com.example.themoviedb.databinding.ActivityMovieBinding
import com.example.themoviedb.model.GenresItem
import com.example.themoviedb.model.MoviesItem
import com.example.themoviedb.ui.ViewModelFactory
import com.example.themoviedb.ui.detail.DetailActivity
import com.example.themoviedb.utils.ItemCallback

class MovieActivity : AppCompatActivity(), ItemCallback {
    private lateinit var adapter: MoviesAdapter
    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)

        val extras = intent.extras
        if (extras != null) {
            val genreId = extras.getInt(EXTRA_DATA, 0)
            viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            viewModel.getMovies(genreId).observe(this) { movies ->
                binding.rvMovies.adapter?.let { adapter ->
                    when (adapter) {
                        is MoviesAdapter -> adapter.setData(movies)
                    }
                }
            }
        }

        showRecyclerView()
    }

    private fun showRecyclerView() {
        adapter = MoviesAdapter(this@MovieActivity)

        binding.rvMovies.layoutManager = GridLayoutManager(this, 3)
        binding.rvMovies.adapter = adapter
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onGenreClicked(data: GenresItem) {
    }

    override fun onMovieClicked(data: MoviesItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_DATA, data.id)
        startActivity(intent)
    }
}