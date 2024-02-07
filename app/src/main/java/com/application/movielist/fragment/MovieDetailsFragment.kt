package com.application.movielist.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.R
import com.application.movielist.activity.MainActivity
import com.application.movielist.adapters.ActorListAdapter
import com.application.movielist.data.MovieData
import com.application.movielist.databinding.FragmentMovieDetailsBinding
import com.bumptech.glide.Glide

class MovieDetailsFragment : Fragment() {

    private lateinit var movie: MovieData
    private lateinit var binding: FragmentMovieDetailsBinding
    private var movieDetailsClick: MovieDetailsClick? = null
    private val actorListAdapter = ActorListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt(MovieListFragment.MOVIE_ID)
        movie = MainActivity.movies.single { it.id == movieId }
        actorListAdapter.updateActors(movie.actors)

        with(binding) {
            if (movie.actors.isEmpty())
                castTitle.visibility = View.GONE
            backButtonText.setOnClickListener { movieDetailsClick?.onBackClick() }
            actorListRv.apply {
                adapter = actorListAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
            Glide.with(root).load(movie.backdrop).into(mask)
            movieTitle.text = movie.title
            ageRating13.text = getAgeRating(movie.minimumAge)
            storylineTv.text = movie.overview
            reviewsCount.text = "${movie.numberOfRatings} REVIEWS"
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieDetailsClick) {
            movieDetailsClick = context
        }
    }

    interface MovieDetailsClick {
        fun onBackClick()
    }

    private fun getAgeRating(minimumAge: Int): String {
        val setAgeView: Int =
            if (minimumAge < 16) R.string.age_rating_13 else R.string.age_rating_16
        return getString(setAgeView)
    }

    companion object {
        const val TAG = "MovieDetailsFragment"
        fun newInstance(bundle: Bundle): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}