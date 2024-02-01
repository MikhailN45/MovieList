package com.application.movielist.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.movielist.fragment.MovieListFragment
import com.application.movielist.R
import com.application.movielist.databinding.ActivityMainBinding
import com.application.movielist.fragment.MovieDetailsFragment

class MainActivity : AppCompatActivity(), MovieDetailsFragment.MovieDetailsCL {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rootFragment: MovieListFragment
    private lateinit var detailsFragment: MovieDetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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



