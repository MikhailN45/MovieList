package com.application.movielist.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.application.movielist.fragment.MovieListFragment
import com.application.movielist.R
import com.application.movielist.contract.Navigator
import com.application.movielist.databinding.ActivityMainBinding
import com.application.movielist.fragment.MovieDetailsFragment

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = MovieListFragment()

            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, fragment)
                .commit()

        }
    }

    override fun goMovieDetailsScreen() {
        launchFragment(MovieDetailsFragment())
    }

    override fun goMovieListScreen() {
        launchFragment(MovieListFragment())
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }

}

