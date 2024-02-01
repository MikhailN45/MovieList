package com.application.movielist.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.data.MovieData
import com.application.movielist.databinding.ViewHolderMovieBinding

class MovieListAdapter(private val movieClickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private var movies: List<MovieData> = listOf()

    class MovieViewHolder(
        val binding: ViewHolderMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderMovieBinding.inflate(inflater, parent, false)

        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        with(holder.binding) {
            moviePreview.setImageResource(movie.poster)
            ageRating.setImageResource(movie.ageRating)
            like.setImageResource(movie.like)
            cardName.text = movie.title
            minutes.text = "${movie.length} MIN"
            ratingBar.numStars = movie.userRating
            reviews.text = "${movie.reviews} REVIEWS"
            tagLine.text = movie.genres.joinToString(" , ")

            movieClick.setOnClickListener { movieClickListener.onMovieClick() }
        }

    }

    interface MovieClickListener {
        fun onMovieClick()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(newMovies: List<MovieData>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}
