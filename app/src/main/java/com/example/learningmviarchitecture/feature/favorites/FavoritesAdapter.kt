package com.example.learningmviarchitecture.feature.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningmviarchitecture.data.cache.entity.MoviePopularEnt
import com.example.learningmviarchitecture.databinding.ItemMovieBinding

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.ItemHolder>() {

    private val favorites = mutableListOf<MoviePopularEnt>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindItem(favorites[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    inner class ItemHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(entity: MoviePopularEnt) {
            binding.tvTitle.text = entity.name
        }
    }

    fun addAllMovies(favorites: List<MoviePopularEnt>) {
        this.favorites.apply {
            clear()
            addAll(favorites)
        }
        notifyDataSetChanged()
    }
}