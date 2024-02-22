package com.application.movielist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.R
import com.application.movielist.adapters.MovieListAdapter
import com.application.movielist.data.MovieData
import com.application.movielist.databinding.FragmentMovieListBinding
import com.application.movielist.repository.Repository
import com.application.movielist.viewmodels.MovieListViewModelFactory
import com.application.movielist.viewmodels.ViewModelMovieList

class MovieListFragment : Fragment(), MovieListAdapter.MovieClickListener {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ViewModelMovieList
    private var progressBar: View? = null

    companion object {
        const val TAG = "MovieListFragment"
        const val MOVIE_ID = "movieId"
        fun newInstance(): MovieListFragment = MovieListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Repository()
        val viewModelFactory = MovieListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModelMovieList::class.java]
        viewModel.getMovies()
        recyclerView = binding.movieListRv
        recyclerView.let {
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            it.adapter = MovieListAdapter(this)
        }
        viewModel.movieListLiveData.observe(viewLifecycleOwner) {
            (recyclerView.adapter as MovieListAdapter).submitList(viewModel.movieListLiveData.value)
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            progressBar?.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    override fun onMovieClick(movie: MovieData) {
        val bundle = Bundle()
        bundle.putInt(MOVIE_ID, movie.kinopoiskId)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragment_container_view,
                MovieDetailsFragment.newInstance(bundle),
                MovieDetailsFragment.TAG
            )
            .addToBackStack(MovieDetailsFragment.TAG)
            .commit()
    }
}
