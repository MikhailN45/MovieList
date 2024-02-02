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

    class ActorViewHolder(private val listItem: View) : RecyclerView.ViewHolder(listItem) {
        private val actorName: TextView = listItem.findViewById(R.id.actorName)
        private val actorImage: ImageView = listItem.findViewById(R.id.actorImage)
        fun bind(actor: ActorData) {
            actorName.text = actor.name
            Glide.with(listItem).load(actor.picture).into(actorImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {

        return ActorViewHolder(
            listItem = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_actor, parent, false)
        )
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
