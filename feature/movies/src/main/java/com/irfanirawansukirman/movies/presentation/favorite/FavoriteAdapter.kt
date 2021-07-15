package com.irfanirawansukirman.movies.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irfanirawansukirman.movies.databinding.MoviesItemBinding
import com.irfanirawansukirman.movies.presentation.movies.model.MoviesUiModel

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ItemHolder>() {

    private val movies = mutableListOf<MoviesUiModel>()

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

        fun bindItem(movie: MoviesUiModel) {
            binding.tvTitle.text = movie.name
        }
    }

    fun addAllMovies(movies: List<MoviesUiModel>) {
        this.movies.apply {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }
}