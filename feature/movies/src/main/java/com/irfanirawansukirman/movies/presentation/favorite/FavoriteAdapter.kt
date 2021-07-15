package com.irfanirawansukirman.movies.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irfanirawansukirman.cache.entity.MoviesPopularEnt
import com.irfanirawansukirman.movies.databinding.MoviesItemBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ItemHolder>() {

    private val movies = mutableListOf<MoviesPopularEnt>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            MoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindItem(movies[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ItemHolder(private val binding: MoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(movie: MoviesPopularEnt) {
            binding.tvTitle.text = movie.name
        }
    }

    fun addAllMovies(movies: List<MoviesPopularEnt>) {
        this.movies.apply {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }
}