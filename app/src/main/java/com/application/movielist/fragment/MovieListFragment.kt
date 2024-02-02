package com.application.movielist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.R
import com.application.movielist.activity.MainActivity
import com.application.movielist.adapters.MovieListAdapter
import com.application.movielist.data.MovieData
import com.application.movielist.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment(), MovieListAdapter.MovieClickListener {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieListAdapter

    private var movies: List<MovieData> = listOf()

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
        movies = MainActivity.movies
        recyclerView = binding.movieListRv
        adapter = MovieListAdapter(this)
        recyclerView.let {
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            it.adapter = adapter
            adapter.updateMovies(movies)
            super.onViewCreated(view, savedInstanceState)
        }
    }

    override fun onMovieClick(movie: MovieData) {
        val bundle = Bundle()
        bundle.putInt(MOVIE_ID, movie.id)
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

