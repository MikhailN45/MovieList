package com.application.movielist.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.data.ActorData
import com.application.movielist.databinding.ViewHolderActorBinding
import com.bumptech.glide.Glide

class ActorListAdapter : RecyclerView.Adapter<ActorListAdapter.ActorViewHolder>() {

    private var actors: List<ActorData> = listOf()

    class ActorViewHolder(private val binding: ViewHolderActorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: ActorData) = with(binding) {
            actorName.text = actor.nameRu
            Glide.with(root).load(actor.posterUrl).into(actorImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
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