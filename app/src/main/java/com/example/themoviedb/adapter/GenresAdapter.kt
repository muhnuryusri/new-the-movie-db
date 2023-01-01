package com.example.themoviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.themoviedb.BuildConfig.BASE_URL
import com.example.themoviedb.BuildConfig.IMAGE_URL
import com.example.themoviedb.databinding.ItemGenreBinding
import com.example.themoviedb.model.GenresItem
import com.example.themoviedb.model.GenresResponse
import com.example.themoviedb.utils.ItemCallback

class GenresAdapter(private val callback: ItemCallback): RecyclerView.Adapter<GenresAdapter.ViewHolder>() {
    private val listItems = ArrayList<GenresItem>()

    fun setData(data: List<GenresItem>) {
        listItems.clear()
        listItems.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    inner class ViewHolder(private val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GenresItem) {
            with(binding) {
                tvGenre.text = data.name

                genreItem.setOnClickListener {
                    callback.onGenreClicked(data)
                }
            }
        }
    }
}