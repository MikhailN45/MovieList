package com.application.movielist.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.movielist.data.FootageResponse
import com.application.movielist.databinding.ViewHolderFootageBinding
import com.bumptech.glide.Glide

class FootageListAdapter : RecyclerView.Adapter<FootageViewHolder>() {

    private var footage: List<FootageResponse> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderFootageBinding.inflate(layoutInflater, parent, false)
        return FootageViewHolder(binding)
    }

    override fun getItemCount(): Int = footage.size

    override fun onBindViewHolder(holder: FootageViewHolder, position: Int) {
        holder.bind(footage[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateFootage(newPictures: List<FootageResponse>) {
        footage = newPictures
        notifyDataSetChanged()
    }
}

class FootageViewHolder(private val binding: ViewHolderFootageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(footage: FootageResponse) = with(binding) {
        Glide.with(root).load(footage.previewUrl).into(footageImage)
    }
}