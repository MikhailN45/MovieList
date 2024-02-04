package com.application.movielist.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.R
import com.application.movielist.data.ActorData
import com.application.movielist.databinding.ViewHolderActorBinding
import com.bumptech.glide.Glide

class ActorListAdapter : RecyclerView.Adapter<ActorListAdapter.ActorViewHolder>() {

    private var actors: List<ActorData> = listOf()

    class ActorViewHolder(private val binding: ViewHolderActorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: ActorData) = with(binding) {
            actorName.text = actor.name
            Glide.with(root).load(actor.picture).into(actorImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        //TODO(extract code to companion)
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderActorBinding.inflate(layoutInflater, parent, false)
        return ActorViewHolder(binding)
    }

    override fun getItemCount(): Int = actors.size


    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateActors(newActors: List<ActorData>) {
        actors = newActors
        notifyDataSetChanged()
    }
}
