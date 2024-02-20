package com.application.movielist.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.R
import com.application.movielist.data.MovieDataResponse
import com.application.movielist.databinding.ViewHolderMovieBinding
import com.application.movielist.utils.Utils
import com.bumptech.glide.Glide

class MovieListAdapter(private val movieClickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<MovieDataResponse> = listOf()

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
        fun onMovieClick(movie: MovieDataResponse)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(newMovies: List<MovieDataResponse>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}

class MovieViewHolder(
    private val clickListener: MovieListAdapter.MovieClickListener,
    private val binding: ViewHolderMovieBinding
) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(movie: MovieDataResponse) = with(binding) {
        Glide
            .with(binding.root)
            .load(movie.posterUrl)
            .into(moviePreview)
        ageRating.setImageResource(Utils.getAgeRatingImg(movie.ratingAgeLimits))
        like.setImageResource(R.drawable.like)
        cardName.text = movie.nameRu
        ratingBar.rating = movie.ratingKinopoisk/2
        tagLine.text = Utils.getTags(movie.genres)
        reviews.text = "${movie.ratingKinopoiskVoteCount} REVIEWS"
        minutes.text = "${movie.filmLength} MIN"
        movieClick.setOnClickListener { clickListener.onMovieClick(movie) }
    }
}