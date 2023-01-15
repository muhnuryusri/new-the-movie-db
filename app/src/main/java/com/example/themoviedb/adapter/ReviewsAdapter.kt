package com.example.themoviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.themoviedb.BuildConfig
import com.example.themoviedb.databinding.ItemMovieBinding
import com.example.themoviedb.databinding.ItemReviewBinding
import com.example.themoviedb.model.MoviesItem
import com.example.themoviedb.model.ReviewItem

class ReviewsAdapter: RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {
    private val listItems = ArrayList<ReviewItem>()

    fun setData(data: List<ReviewItem>) {
        listItems.clear()
        listItems.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    inner class ViewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ReviewItem) {
            with(binding) {
                tvName.text = data.author
                tvReview.text = data.content
            }
        }
    }
}