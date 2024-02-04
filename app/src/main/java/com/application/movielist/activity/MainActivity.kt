package com.application.movielist.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.application.movielist.fragment.MovieListFragment
import com.application.movielist.R
import com.application.movielist.data.MovieData
import com.application.movielist.data.loadMovies
import com.application.movielist.databinding.ActivityMainBinding
import com.application.movielist.fragment.MovieDetailsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MovieDetailsFragment.MovieDetailsClick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rootFragment: MovieListFragment
    private lateinit var detailsFragment: MovieDetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadMovies()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            rootFragment = MovieListFragment.newInstance()

            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container_view,
                    rootFragment,
                    MovieListFragment.TAG
                )
                .commit()
        } else {
            val movieListFragment = supportFragmentManager.findFragmentByTag(MovieListFragment.TAG)
            rootFragment = movieListFragment as MovieListFragment

            val movieDetailsFragment = supportFragmentManager.findFragmentByTag(MovieDetailsFragment.TAG)
            detailsFragment = movieDetailsFragment as MovieDetailsFragment
        }
    }

    override fun onBackClick() {
        onBackPressed()
    }

    private fun loadMovies() {
        lifecycleScope.launch(Dispatchers.IO) {
            movies = loadMovies(applicationContext)
        }
    }

    companion object {
        var movies: List<MovieData> = listOf()
    }
}



