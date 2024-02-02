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
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MovieDetailsFragment.MovieDetailsCL {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rootFragment: MovieListFragment
    private lateinit var detailsFragment: MovieDetailsFragment

    companion object {
        var movies: List<MovieData> = listOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val coroutine = async(Dispatchers.IO) {
                movies = loadMovies(applicationContext)
            }
            coroutine.await()
        }

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
            val movieList = supportFragmentManager.findFragmentByTag(MovieListFragment.TAG)
            rootFragment = movieList as MovieListFragment

            val movieDetails = supportFragmentManager.findFragmentByTag(MovieDetailsFragment.TAG)
            if (movieDetails != null) {
                detailsFragment = movieDetails as MovieDetailsFragment
            }
        }
    }

    override fun onBackClick() {
        onBackPressed()
    }
}



