package com.application.movielist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.movielist.contract.navigator
import com.application.movielist.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        binding.backButtonText.setOnClickListener { goMovieListScreen() }

        return binding.root
    }

    private fun goMovieListScreen() {
        navigator().goMovieListScreen()
    }

}
