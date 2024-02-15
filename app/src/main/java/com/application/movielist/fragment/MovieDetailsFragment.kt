package com.application.movielist.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.adapters.ActorListAdapter
import com.application.movielist.data.MovieData
import com.application.movielist.databinding.FragmentMovieDetailsBinding
import com.application.movielist.utils.Utils
import com.application.movielist.viewmodels.ViewModelMovieDetails
import com.bumptech.glide.Glide

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private var movieDetailsClick: MovieDetailsClick? = null
    private val actorListAdapter = ActorListAdapter()
    private val viewModelMovieDetails: ViewModelMovieDetails by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = arguments?.getInt(MovieListFragment.MOVIE_ID)

        viewModelMovieDetails.getMovie(movieId!!)

        viewModelMovieDetails.movieLiveData.observe(viewLifecycleOwner) { movie: MovieData ->
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
                ageRating13.text = Utils.getRatingStringInt(movie.minimumAge)
                storylineTv.text = movie.overview
                val reviewsCountText = "${movie.numberOfRatings} REVIEWS"
                reviewsCount.text = reviewsCountText
                actorListAdapter.updateActors(movie.actors)
            }
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

    companion object {
        const val TAG = "MovieDetailsFragment"
        fun newInstance(bundle: Bundle): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}