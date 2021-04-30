package com.exemple.filmnews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exemple.filmnews.databinding.ItemMovieBinding

class MovieAdapter(private val listener: OnItemClickListener): ListAdapter<Movie, MovieAdapter.MyViewHolder>(DiffCallback()) {

    inner class MyViewHolder(private val binding: ItemMovieBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val movie = getItem(position)
                        listener.onItemClick(movie, itemMovieImg)
                    }
                }
            }
        }

        fun bind(movie: Movie) {
            binding.apply {
                Glide.with(itemMovieImg).load(movie.thumbnail).into(itemMovieImg)
                itemMovieTitle.text = movie.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == newItem

    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie, movieImageView: ImageView)
    }
}


