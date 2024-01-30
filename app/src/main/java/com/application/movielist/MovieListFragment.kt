package com.application.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.application.movielist.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)

        binding.filmPreviewBackgroundTransparent.setOnClickListener { openFilmDetails() }

        return binding.root
    }

    private fun openFilmDetails() {
        val fragment = MovieDetailsFragment()

        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container_view, fragment)
            .commit()

    }
}
