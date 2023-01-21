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

                val genre = when(data.name) {
                    "Action" -> "/s16H6tpK2utvwDtzZ8Qy4qm5Emw.jpg"
                    "Adventure" -> "/53BC9F2tpZnsGno2cLhzvGprDYS.jpg"
                    "Animation" -> "/tGwO4xcBjhXC0p5qlkw37TrH6S6.jpg"
                    "Comedy" -> "/ecaB1LUoQE1ylZJVF5KLRTkewt8.jpg"
                    "Crime" -> "/g9Kb3RaLjsybI1jpqHQ3QZTCYpB.jpg"
                    "Documentary" -> "/hraytJBd1Ztcp7k9So4zLFcgsyh.jpg"
                    "Drama" -> "/gkseI3CUfQtMKX41XD4AxDzhQb7.jpg"
                    "Family" -> "/6rax7iv5yQtldc3ApEvrheJf8uw.jpg"
                    "Fantasy" -> "/sKK5bbQm15jzbMRwCJmPBbv9trN.jpg"
                    "History" -> "/maxRhndtC4BJABvqRPvYNu69CHA.jpg"
                    "Horror" -> "/7dm64SW5L5CCg47kAEAcdCGaq5i.jpg"
                    "Music" -> "/7tfQMNy63H4PxuFkp52I7aEkrcW.jpg"
                    "Mystery" -> "/dKqa850uvbNSCaQCV4Im1XlzEtQ.jpg"
                    "Romance" -> "/rl7Jw8PjhSIjArOlDNv0JQPL1ZV.jpg"
                    "Science Fiction" -> "/yYrvN5WFeGYjJnRzhY0QXuo4Isw.jpg"
                    "TV Movie" -> "/ggS7TQgu34Lct30MFwNlxVRJOia.jpg"
                    "Thriller" -> "/b08BDQPq42AoLMfhi7DtTOoYqVu.jpg"
                    "War" -> "/mqsPyyeDCBAghXyjbw4TfEYwljw.jpg"
                    "Western" -> "/jtFy2m7CvOVTYoNTY9arN8DVHFi.jpg"
                    else -> {}
                }


                Glide.with(itemView.context)
                    .load(IMAGE_URL + genre)
                    .centerCrop()
                    .apply(RequestOptions().override(500, 700))
                    .into(imgBackground)

                tvGenre.text = data.name
                genreItem.setOnClickListener {
                    callback.onGenreClicked(data)
                }
            }
        }
    }
}