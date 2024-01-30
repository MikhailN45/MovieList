package com.application.movielist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.movielist.contract.navigator
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
        navigator().goMovieDetailsScreen()
    }
}
