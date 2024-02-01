package com.application.movielist.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.data.ActorData
import com.application.movielist.databinding.ViewHolderActorBinding

class ActorListAdapter : RecyclerView.Adapter<ActorListAdapter.ActorViewHolder>() {

    private var actors: List<ActorData> = listOf()

    class ActorViewHolder(val binding: ViewHolderActorBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderActorBinding.inflate(inflater, parent, false)
        return ActorViewHolder(binding)
    }

    override fun getItemCount(): Int = actors.size


    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actors[position]
        with(holder.binding) {
            actorName.text = actor.actorName
            actorImage.setImageResource(actor.actorImage)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateActors(newActors: List<ActorData>) {
        actors = newActors
        notifyDataSetChanged()
    }
}