package com.example.themoviedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedb.adapter.GenresAdapter
import com.example.themoviedb.databinding.ActivityMainBinding
import com.example.themoviedb.model.GenresItem
import com.example.themoviedb.model.MoviesItem
import com.example.themoviedb.ui.MainViewModel
import com.example.themoviedb.ui.ViewModelFactory
import com.example.themoviedb.ui.detail.DetailActivity
import com.example.themoviedb.ui.movie.MovieActivity
import com.example.themoviedb.ui.movie.MovieActivity.Companion.EXTRA_DATA
import com.example.themoviedb.utils.ItemCallback

class MainActivity : AppCompatActivity(), ItemCallback {
    private lateinit var adapter: GenresAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.getGenres().observe(this) { genres ->
            binding.rvGenres.adapter?.let { adapter ->
                when (adapter) {
                    is GenresAdapter -> adapter.setData(genres)
                }
            }
        }

        showRecyclerView()
    }

    private fun showRecyclerView() {
        adapter = GenresAdapter(this@MainActivity)

        binding.rvGenres.layoutManager = LinearLayoutManager(this)
        binding.rvGenres.adapter = adapter
    }

    override fun onGenreClicked(data: GenresItem) {
        val intent = Intent(this, MovieActivity::class.java)
        intent.putExtra(EXTRA_DATA, data.id)
        startActivity(intent)
    }

    override fun onMovieClicked(data: MoviesItem) {
    }
}