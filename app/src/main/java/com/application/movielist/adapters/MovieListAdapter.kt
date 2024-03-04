package com.application.movielist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.R
import com.application.movielist.data.MovieDataResponse
import com.application.movielist.databinding.ViewHolderMovieBinding
import com.application.movielist.utils.Utils.getRating
import com.application.movielist.utils.Utils.getReviews
import com.application.movielist.utils.Utils.getTags
import com.bumptech.glide.Glide

class MovieListAdapter :
    ListAdapter<MovieDataResponse, MovieListAdapter.MovieViewHolder>(DiffCallback()) {

    var movieClickListener: MovieClickListener? = null

    interface MovieClickListener {
        fun onMovieClick(movie: MovieDataResponse)
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
        fun bind(movie: MovieDataResponse) =
            with(binding) {
                Glide
                    .with(binding.root)
                    .load(movie.posterUrl)
                    .into(moviePreview)
                yearText.text = movie.year
                like.setImageResource(R.drawable.like_gray)
                cardName.text = movie.nameRu
                ratingText.text = getRating(movie.rating)
                tagLine.text = getTags(movie.genreResponses)
                reviews.text = getReviews(movie)
                minutes.text = movie.filmLength

                movieClick.setOnClickListener { movieClickListener?.onMovieClick(movie) }
                if (ratingText.text == "0") ratingText.visibility = View.GONE
                if (minutes.text.isNullOrEmpty()) minutes.visibility = View.GONE
                if (tagLine.text.isNullOrEmpty()) tagLine.visibility = View.GONE
                if (reviews.text.isNullOrEmpty()) reviews.visibility = View.GONE
            }
    }

    class DiffCallback : DiffUtil.ItemCallback<MovieDataResponse>() {
        override fun areItemsTheSame(
            oldItem: MovieDataResponse,
            newItem: MovieDataResponse
        ): Boolean {
            return oldItem.nameRu == newItem.nameRu
        }

        override fun areContentsTheSame(
            oldItem: MovieDataResponse,
            newItem: MovieDataResponse
        ): Boolean {
            return oldItem == newItem
        }
    }
}