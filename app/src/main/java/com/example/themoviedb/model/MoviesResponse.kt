package com.example.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MoviesResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<MoviesItem?>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)

@Parcelize
data class MoviesItem(

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	@field:SerializedName("imdb_id")
	val imdbId: String? = null,

	@field:SerializedName("video")
	val video: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("revenue")
	val revenue: Int? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItem?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null,

	@field:SerializedName("budget")
	val budget: Int? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("original_title")
	val originalTitle: String? = null,

	@field:SerializedName("runtime")
	val runtime: Int? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("tagline")
	val tagline: String? = null,

	@field:SerializedName("adult")
	val adult: Boolean? = null,

	@field:SerializedName("homepage")
	val homepage: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable