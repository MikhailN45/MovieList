package com.application.movielist


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.movielist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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
}

