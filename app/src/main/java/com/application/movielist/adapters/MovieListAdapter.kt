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
import com.application.movielist.databinding.FragmentMovieListBinding
import com.application.movielist.databinding.ViewHolderActorBinding
import com.application.movielist.databinding.ViewHolderMovieBinding
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class MovieListAdapter(private val movieClickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<MovieData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderMovieBinding.inflate(inflater, parent, false)

        return MovieViewHolder(movieClickListener, binding)
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
    private val clickListener: MovieListAdapter.MovieClickListener,
    private val binding: ViewHolderMovieBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieData) = with(binding) {
        Glide
            .with(binding.root)
            .load(movie.poster)
            .into(moviePreview)
        ageRating.setImageResource(getAgeRatingImg(movie.minimumAge))
        like.setImageResource(R.drawable.like)
        cardName.text = movie.title
        ratingBar.rating = convertRating(movie.ratings)
        tagLine.text = getTags(movie.genres)
        //TODO(read data from getString()
        reviews.text = "${movie.numberOfRatings} REVIEWS"
        minutes.text = "${movie.runtime} MIN"
        movieClick.setOnClickListener { clickListener.onMovieClick(movie) }
    }

    //TODO(extract utils fun)

    private fun getTags(genres: List<GenreData>): String = genres.joinToString(", ") { it.name }

    private fun convertRating(ratings: Float): Float = ratings / 2.0f

    private fun getAgeRatingImg(minimumAge: Int): Int =
        if (minimumAge >= 16) R.drawable.rating16 else R.drawable.rating13
}

