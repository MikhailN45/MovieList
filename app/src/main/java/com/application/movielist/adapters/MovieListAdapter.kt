package com.application.movielist.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.R
import com.application.movielist.data.MovieData
import com.application.movielist.databinding.ViewHolderMovieBinding
import com.application.movielist.utils.Utils
import com.bumptech.glide.Glide

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
    @SuppressLint("SetTextI18n")
    fun bind(movie: MovieData) = with(binding) {
        Glide
            .with(binding.root)
            .load(movie.poster)
            .into(moviePreview)
        ageRating.setImageResource(Utils.getAgeRatingImg(movie.minimumAge))
        like.setImageResource(R.drawable.like)
        cardName.text = movie.title
        ratingBar.rating = movie.ratings
        tagLine.text = Utils.getTags(movie.genres)
        reviews.text = "${movie.numberOfRatings} REVIEWS"
        minutes.text = "${movie.runtime} MIN"
        movieClick.setOnClickListener { clickListener.onMovieClick(movie) }
    }
}