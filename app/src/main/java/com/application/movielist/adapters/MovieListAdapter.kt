package com.application.movielist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.R
import com.application.movielist.data.MovieData
import com.application.movielist.databinding.ViewHolderMovieBinding
import com.application.movielist.utils.Utils.getRating
import com.application.movielist.utils.Utils.getReviews
import com.application.movielist.utils.Utils.getTags
import com.bumptech.glide.Glide

class MovieListAdapter :
    ListAdapter<MovieData, MovieListAdapter.MovieViewHolder>(DiffCallback()) {

    var movieClickListener: MovieClickListener? = null

    interface MovieClickListener {
        fun onMovieClick(movie: MovieData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class MovieViewHolder(
        private val binding: ViewHolderMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieData) =
            with(binding) {
                Glide
                    .with(binding.root)
                    .load(movie.posterUrl)
                    .into(moviePreview)
                yearText.text = movie.year
                like.setImageResource(R.drawable.like)
                cardName.text = movie.nameRu
                ratingBar.rating = getRating(movie.rating)
                tagLine.text = getTags(movie.genres)
                reviews.text = getReviews(movie)
                minutes.text = movie.filmLength
                movieClick.setOnClickListener { movieClickListener?.onMovieClick(movie) }
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