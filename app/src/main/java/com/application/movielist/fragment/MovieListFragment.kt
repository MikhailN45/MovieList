package com.application.movielist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.R
import com.application.movielist.adapters.MovieListAdapter
import com.application.movielist.data.MovieData
import com.application.movielist.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment(), MovieListAdapter.MovieClickListener {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieListAdapter

    private val movies: List<MovieData> = listOf(
        MovieData(
            R.drawable.avengers_small,
            R.drawable.rating13,
            R.drawable.like,
            "Avengers: End Game",
            137,
            4,
            125,
            listOf("Action", "Adventure", "Fantasy")
        ),
        MovieData(
            R.drawable.tenet,
            R.drawable.rating16,
            R.drawable.like,
            "Tenet",
            97,
            5,
            98,
            listOf("Action", "Sci-Fi", "Thriller")
        ),
        MovieData(
            R.drawable.black_widow,
            R.drawable.rating13,
            R.drawable.like,
            "Black Widow",
            102,
            4,
            38,
            listOf("Action", "Adventure", "Sci-Fi")
        ),
        MovieData(
            R.drawable.superwoman,
            R.drawable.rating13,
            R.drawable.like,
            "Wonder Woman",
            120,
            5,
            74,
            listOf("Action", "Adventure", "Fantasy")
        )
    )

    companion object {
        const val TAG = "MovieListFragment"
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
        recyclerView = binding.movieListRv
        adapter = MovieListAdapter(this)
        recyclerView.let {
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            it.adapter = adapter
            adapter.updateMovies(movies)
            super.onViewCreated(view, savedInstanceState)
        }
    }

    override fun onMovieClick() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragment_container_view,
                MovieDetailsFragment.newInstance(),
                MovieDetailsFragment.TAG
            )
            .addToBackStack(MovieDetailsFragment.TAG)
            .commit()
    }
}

