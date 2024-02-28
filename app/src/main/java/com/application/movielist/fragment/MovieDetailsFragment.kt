package com.application.movielist.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.adapters.FootageListAdapter
import com.application.movielist.data.FootageList
import com.application.movielist.data.MovieInfo
import com.application.movielist.databinding.FragmentMovieDetailsBinding
import com.application.movielist.repository.Repository
import com.application.movielist.utils.Utils.getAgeRating
import com.application.movielist.utils.Utils.getReviewsForInfo
import com.application.movielist.utils.Utils.getTags
import com.application.movielist.viewmodels.MovieDetailsViewModelFactory
import com.application.movielist.viewmodels.ViewModelMovieDetails
import com.bumptech.glide.Glide

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var viewModel: ViewModelMovieDetails
    private var movieDetailsClick: MovieDetailsClick? = null
    private val footageListAdapter = FootageListAdapter()

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
        val repository = Repository()
        val viewModelFactory = MovieDetailsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModelMovieDetails::class.java]
        viewModel.getMovie(movieId!!)
        binding.footageListRv.apply {
            adapter = footageListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)


            viewModel.footageLiveData.observe(viewLifecycleOwner) { footage: FootageList ->
                with(binding) {
                    if (footage.items.isNotEmpty())
                        footageTitle.visibility = View.VISIBLE
                        footageListAdapter.updateFootage(footage.items)
                }

                viewModel.loadingLiveData.observe(viewLifecycleOwner) {
                    binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
                }
            }


            viewModel.movieLiveData.observe(viewLifecycleOwner) { movie: MovieInfo ->
                with(binding) {
                    backButtonText.setOnClickListener { movieDetailsClick?.onBackClick() }
                    Glide.with(root)
                        .load(movie.posterUrl)
                        .into(mask)
                    movieTitle.text = movie.nameRu
                    movieGenres.text = getTags(movie.genres)
                    ageRating.text = getAgeRating(movie.ratingAgeLimits)
                    storylineTv.text = movie.description
                    reviewsCount.text = getReviewsForInfo(movie)
                }
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