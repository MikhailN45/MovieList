package com.application.movielist.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.R
import com.application.movielist.activity.MainActivity
import com.application.movielist.adapters.ActorListAdapter
import com.application.movielist.data.ActorData
import com.application.movielist.data.MovieData
import com.application.movielist.databinding.FragmentMovieDetailsBinding
import com.bumptech.glide.Glide

class MovieDetailsFragment : Fragment() {

    private lateinit var movie: MovieData

    private var onBackButtonCL: MovieDetailsCL? = null
    private lateinit var recyclerView: RecyclerView

    companion object {
        const val TAG = "MovieDetailsFragment"
        fun newInstance(bundle: Bundle): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movie_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = view.findViewById<TextView>(R.id.movie_title)
        val poster = view.findViewById<ImageView>(R.id.mask)
        val ageRating = view.findViewById<TextView>(R.id.age_rating_13)
        val storyLine = view.findViewById<TextView>(R.id.storyline_tv)
        val movieId = arguments?.getInt(MovieListFragment.MOVIE_ID)
        movie = MainActivity.movies.single { it.id == movieId }
        view.findViewById<TextView>(R.id.back_button_text)
            .setOnClickListener { onBackButtonCL?.onBackClick() }


        recyclerView = view.findViewById(R.id.actor_list_rv)
        recyclerView.adapter = ActorListAdapter()
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        (recyclerView.adapter as ActorListAdapter).updateActors(movie.actors)

        if (movie.actors.isEmpty())
            view.findViewById<TextView>(R.id.cast_title).visibility = View.GONE

        Glide.with(view).load(movie.backdrop).into(poster)
        title.text = movie.title
        ageRating.text = getAgeRating(movie.minimumAge)
        storyLine.text = movie.overview

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieDetailsCL) {
            onBackButtonCL = context
        }
    }

    interface MovieDetailsCL {
        fun onBackClick()
    }

    private fun getAgeRating(minimumAge: Int): String {
        val setAgeView: Int =
            if (minimumAge < 16) R.string.age_rating_13 else R.string.age_rating_16
        return getString(setAgeView)
    }

}
