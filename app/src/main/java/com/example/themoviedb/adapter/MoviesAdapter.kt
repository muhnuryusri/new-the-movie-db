package com.example.themoviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.themoviedb.BuildConfig
import com.example.themoviedb.databinding.ItemGenreBinding
import com.example.themoviedb.databinding.ItemMovieBinding
import com.example.themoviedb.model.GenresResponse
import com.example.themoviedb.model.MoviesItem
import com.example.themoviedb.utils.ItemCallback

class MoviesAdapter(private val callback: ItemCallback): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private val listItems = ArrayList<MoviesItem>()

    fun setData(data: List<MoviesItem>) {
        listItems.clear()
        listItems.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviesItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + data.posterPath)
                    .centerCrop()
                    .apply(RequestOptions().override(290, 410))
                    .into(imgMovie)

                movieItem.setOnClickListener {
                    callback.onMovieClicked(data)
                }
            }
        }
    }
}