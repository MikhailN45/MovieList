package com.application.movielist.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.R
import com.application.movielist.data.MovieData
import com.application.movielist.databinding.ViewHolderMovieBinding
import com.application.movielist.utils.Utils
import com.bumptech.glide.Glide

class MovieListAdapter(private val movieClickListener: MovieClickListener) :
    ListAdapter<MovieData, MovieListAdapter.MovieViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(movieClickListener, binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    interface MovieClickListener {
        fun onMovieClick(movie: MovieData)
    }


    class MovieViewHolder(
        private val clickListener: MovieClickListener,
        private val binding: ViewHolderMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(movie: MovieData) = with(binding) {
            Glide
                .with(binding.root)
                .load(movie.posterUrl)
                .into(moviePreview)
            ageRating.setImageResource(Utils.getAgeRatingImg(movie.ratingAgeLimits))
            like.setImageResource(R.drawable.like)
            cardName.text = movie.nameRu
            ratingBar.rating = movie.ratingKinopoisk / 2
            tagLine.text = Utils.getTags(movie.genres)
            reviews.text = "${movie.ratingKinopoiskVoteCount} REVIEWS"
            minutes.text = "${movie.filmLength} MIN"
            movieClick.setOnClickListener { clickListener.onMovieClick(movie) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MovieData>() {
        override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem.nameRu == newItem.nameRu
        }

        override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem == newItem
        }
    }
}