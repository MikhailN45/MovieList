package com.application.movielist.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.R
import com.application.movielist.data.GenreData
import com.application.movielist.data.MovieData
import com.application.movielist.databinding.ViewHolderMovieBinding
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class MovieListAdapter(private val movieClickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<MovieData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
        return MovieViewHolder(view, movieClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    interface MovieClickListener {
        fun onMovieClick(movie: MovieData)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(newMovies: List<MovieData>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}

class MovieViewHolder(
    private val movieItem: View,
    private val clickListener: MovieListAdapter.MovieClickListener
) : RecyclerView.ViewHolder(movieItem) {
    private val movieBackground: ImageView = movieItem.findViewById(R.id.movie_preview)
    private val ageRating: ImageView = movieItem.findViewById(R.id.age_rating)
    private val like: ImageView = movieItem.findViewById(R.id.like)
    private val movieTitle: TextView = movieItem.findViewById(R.id.card_name)
    private val movieLength: TextView = movieItem.findViewById(R.id.minutes)
    private val movieRating: RatingBar = movieItem.findViewById(R.id.ratingBar)
    private val reviewsCount: TextView = movieItem.findViewById(R.id.reviews)
    private val tags: TextView = movieItem.findViewById(R.id.tagLine)
    private val clickItem: View = movieItem.findViewById(R.id.movieClick)

    fun bind(movie: MovieData) {
        Glide
            .with(movieItem)
            .load(movie.poster)
            .into(movieBackground)

        ageRating.setImageResource(getAgeRatingImg(movie.minimumAge))
        like.setImageResource(R.drawable.like)
        movieTitle.text = movie.title
        movieRating.rating = convertRating(movie.ratings)
        tags.text = getTags(movie.genres)
        reviewsCount.text = "${movie.numberOfRatings} REVIEWS"
        movieLength.text = "${movie.runtime} MIN"

        clickItem.setOnClickListener { clickListener.onMovieClick(movie) }

    }


    private fun getTags(genres: List<GenreData>): String = genres.joinToString(", ") { it.name }

    private fun convertRating(ratings: Float): Float = ratings / 2.0f

    private fun getAgeRatingImg(minimumAge: Int): Int =
        if (minimumAge >= 16) R.drawable.rating16 else R.drawable.rating13
}

