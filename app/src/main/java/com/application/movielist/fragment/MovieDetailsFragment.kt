package com.application.movielist.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.R
import com.application.movielist.adapters.ActorListAdapter
import com.application.movielist.data.ActorData
import com.application.movielist.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

    private val actors: List<ActorData> = listOf(
        ActorData(
            R.drawable.robert_downey_jr_,
            "Robert \nDowney Jr."
        ),
        ActorData(
            R.drawable.chris_evans,
            "Chris \nEvans"
        ),
        ActorData(
            R.drawable.mark_ruffalo,
            "Mark \nRuffalo"
        ),
        ActorData(
            R.drawable.chris_hemsworth,
            "Chris \nHemsworth"
        )
    )

    private var onBackButtonCL: MovieDetailsCL? = null
    private lateinit var recyclerView: RecyclerView

    companion object {
        const val TAG = "MovieDetailsFragment"
        fun newInstance(): MovieDetailsFragment = MovieDetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backButtonText.setOnClickListener { onBackButtonCL?.onBackClick() }
        recyclerView = binding.actorListRv
        recyclerView.adapter = ActorListAdapter()
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        (recyclerView.adapter as ActorListAdapter).updateActors(actors)
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

}
